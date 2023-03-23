#InMemoryKeyValueStoreWithBasicTransactionSupport
1. Design and implement an in-memory key value datastore. This datastore should be able to
   support some basic operations such as Get, Set and Delete
   Map<Key,Value>
1. Add support for transactions - Begin, Commit and Rollback. A transaction is created with
   the "begin" command and creates a context for the other operations to happen. Until the
   active transaction is committed using the "commit" command, those operations do not persist.
   And, the Rollback command throws away any changes made by those operations in the context of
   the active transaction. Every begin() will always come with a commit() or rollback().