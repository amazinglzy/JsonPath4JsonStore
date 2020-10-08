package jp4js.storage.region;

import jp4js.utils.iter.Iter;

public interface RegionIterator<E> extends Iter<E> {
    void seek(long visit);
}
