package jp4js.storage;

import java.util.List;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

import jp4js.utils.Configuration;
import jp4js.storage.doc.Leaf;
import jp4js.storage.doc.NodeID;


public class MemStoreTest {
    
    @Test
    public void basic01_() {
        Configuration configuration = Configuration.defaultConfiguration();
        String json1 = "[1, 2, 3]";
        String json2 = "{\"name\": \"Alice\", \"age\": 20}";
        Object[] json = {
            configuration.jsonProvider().parse(json1),
            configuration.jsonProvider().parse(json2)
        };
        MemStore store = new MemStore();
        for (int i = 0; i < json.length; i++)
            store.add(json[i], configuration);

        assertThat(store.docsID().size()).isEqualTo(2);

        NodeID id1 = store.docsID().get(0);
        NodeID id2 = store.docsID().get(1);
        List<NodeID> c1 = store.children(id1);
        assertThat(c1.size()).isEqualTo(3);
        for (int i = 0; i < c1.size(); i++)
            assertThat(store.doc(c1.get(i)) instanceof Leaf.IntNode).isTrue();
        for (int i = 0; i < c1.size(); i++) 
            assertThat(((Leaf.IntNode)store.doc(c1.get(i))).data().data()).isEqualTo(i + 1);
        List<NodeID> c2 = store.children(id2);
        assertThat(c2.size()).isEqualTo(2);
        assertThat(store.doc(c2.get(0)) instanceof Leaf.IntNode).isTrue();
    }
}