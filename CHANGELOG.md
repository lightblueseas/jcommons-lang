## Change log
----------------------

Version 5.2
-------------

CHANGED:

- update of parent version to 4.8
- update of model-data version to 1.6.2
- replaced obsolet dependency jobject-core with new dependency jobj-core in version 3
- replaced obsolet dependency jobject-diff with new dependency jobj-differ in version 3
- replaced obsolet dependency jobject-evaluate with new dependency jobj-contract-verifier in version 3 
- tagged several classes and methods as deprecated that moved to other projects and will be removed in a later version

Version 5.1
-------------

CHANGED:

- update of parent version to 4.4
- removed log dependencies
- update of dependency silly-collections version to 5
- update of dependency vintage-time version to 5.1
- update of dependency test-objects version to 5
- update of jobject-extensions version to 2.5
- update of runtime-compiler version to 1.2
- update of model-data version to 1.6.1
- update of meanbean-factories version to 1.1.1

Version 5
-------------

ADDED:
 
- new dependency of model-core in version 1.6

CHANGED:

- update of parent version to 4.2
- update of dependency silly-collections version to 4.35
- update of dependency commons-lang3 version to 3.8.1
- removed joda-time dependency

Version 4.35
-------------

ADDED:
 
- new file extension for encrypted and decrypted files created

CHANGED:

- update of parent version to 4.1
- update of dependency test-objects version to 4.28
- update of dependency silly-collections version to 4.33
- update of dependency vintage-time version to 4.12
- update of jobject-extensions version to 1.12
- update of commons-collections4 version to 4.2
- update of cglib version to 3.2.7

Version 4.34
-------------

CHANGED:

- unit tests extended for improve code coverage
- removed deprecated enum de.alpharogroup.file.checksum.Algorithm
- removed deprecated enum de.alpharogroup.exception.ExceptionType
- tagged several close methods as deprecated in class StreamExtensions since jdk7 the new feature try-with-resources statement
- update of dependency silly-collections version to 4.29

Version 4.33
-------------

ADDED:
 
- provide package.html for package de.alpharogroup.config
- new dependency jobject-evaluate in version 1.11

CHANGED:

- unit tests extended for improve code coverage
- update of jobject-extensions version to 1.11
- update of dependency joda-time version to 2.10

Version 4.32
-------------

ADDED:
 
- new method getTypeArgument over the child class and the index
- new method getFirstTypeArgument over the child class

CHANGED:

- update of parent version to 3.11
- removed unneeded .0 at the end of version
- update of silly-collections version to 4.28.0

Version 4.31.0
-------------

CHANGED:

- handle interface case in method getTypeArguments
- new getTypeArguments method with one argument created

Version 4.30.0
-------------

ADDED:
 
- new method for get the name of the current method
- new method for get the name of the calling method
- new method for get H2 connection created
- new class ConfigurationExtensions created for create configuration files

CHANGED:

- update of parent version and of dependencies versions

Version 4.29.0
-------------

ADDED:
 
- provide package.html for the javadoc of packages
- Donation buttons extended for paypal and bitcoin
- added new meanbean dependency for better unit testing of beans

CHANGED:

- update of parent version and of dependencies versions
- javadoc extended and improved

Notable links:
[keep a changelog](http://keepachangelog.com/en/1.0.0/) Don’t let your friends dump git logs into changelogs
