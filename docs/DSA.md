# Document Set Algebra

Document Set Algebra is an algebra for document domains. Document Domain Algebra defines what is a document
, what document set and what operations can be done by document.

## What is a Document

A Document can be:

1. scalar values, for example: 1, true, false, "Hello", 1.0
2. a mapping from string to document
3. a list of document

A Document can have many representations including json, xml, yaml and so on but document 
represent the information level otherwise what bits to represent the information.

## What is a Document Set

A Document has its own structure. If We specify the structure as a template of a document, 
then the document can be  represent as a collection of small documents, which can construct 
the origin document by filling these small documents into the template.

If A Set Document share a common template, then we call the set the Document Set.

## What is the operators

1. Scan(StructureList)
2. Projection(StructureList)
3. Selection()