# jcommons-lang

<div align="center">

[![Build Status](https://travis-ci.org/lightblueseas/jcommons-lang.svg?branch=develop)](https://travis-ci.org/lightblueseas/jcommons-lang)
[![Coverage Status](https://coveralls.io/repos/github/lightblueseas/jcommons-lang/badge.svg?branch=develop)](https://coveralls.io/github/lightblueseas/jcommons-lang?branch=develop)
[![Open Issues](https://img.shields.io/github/issues/lightblueseas/jcommons-lang.svg?style=flat)](https://github.com/lightblueseas/jcommons-lang/issues)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/de.alpharogroup/jcommons-lang/badge.svg)](https://maven-badges.herokuapp.com/maven-central/de.alpharogroup/jcommons-lang)
[![Javadocs](http://www.javadoc.io/badge/de.alpharogroup/jcommons-lang.svg)](http://www.javadoc.io/doc/de.alpharogroup/jcommons-lang)
[![MIT license](http://img.shields.io/badge/license-MIT-brightgreen.svg?style=flat)](http://opensource.org/licenses/MIT)

</div>

The jcommons-lang project provides several extension methods for java core classes like Class and other extension for Annotations. 
The feature extension methods can be provided over the lombok library. 

## Key features:

- Very small size (<87Kb)
- Extension methods for java core class Class
- Extension methods for java class Class, File for checksum, namefilter and filename filter
- Id generators
- Annotations for import resources like css, js etc.
- Extension methods for jdbc and db connections
- Extension methods for Loggers, regex, shell
- Extension methods for java class Math and String

## License

The source code comes under the liberal MIT License, making jcommons-lang great for all types of applications.

## Maven dependency

Maven dependency is now on sonatype.
Check out [sonatype repository](https://oss.sonatype.org/index.html#nexus-search;gav~de.alpharogroup~jcommons-lang~~~) for latest snapshots and releases.

Add the following maven dependency to your project `pom.xml` if you want to import the core functionality of jcommons-lang:

Than you can add the dependency to your dependencies:

	<properties>
			...
		<!-- JCOMMONS-LANG version -->
		<jcommons-lang.version>5.2</jcommons-lang.version>
			...
	</properties>
			...
		<dependencies>
			...
            <!-- JCOMMONS-LANG DEPENDENCY -->
			<dependency>
				<groupId>de.alpharogroup</groupId>
				<artifactId>jcommons-lang</artifactId>
				<version>${jcommons-lang.version}</version>
			</dependency>
			...
		</dependencies>

## Semantic Versioning

The versions of jcommons-lang are maintained with the Semantic Versioning guidelines.

Release version numbers will be incremented in the following format:

`<major>.<minor>.<patch>`

For detailed information on versioning you can visit the [wiki page](https://github.com/lightblueseas/mvn-parent-projects/wiki/Semantic-Versioning).

## Want to Help and improve it? ###

The source code for jcommons-lang are on GitHub. Please feel free to fork and send pull requests!

Create your own fork of [lightblueseas/jcommons-lang/fork](https://github.com/lightblueseas/jcommons-lang/fork)

To share your changes, [submit a pull request](https://github.com/lightblueseas/jcommons-lang/pull/new/develop).

Don't forget to add new units tests on your changes.

## Contacting the Developers

Do not hesitate to contact the jcommons-lang developers with your questions, concerns, comments, bug reports, or feature requests.
- Feature requests, questions and bug reports can be reported at the [issues page](https://github.com/lightblueseas/jcommons-lang/issues).

## Note

No animals were harmed in the making of this library.

# Donations

This project is kept as an open source product and relies on contributions to remain being developed. 
If you like this project, please consider a donation through paypal: <a href="https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=B37J9DZF6G9ZC" target="_blank">
<img src="https://www.paypalobjects.com/en_US/GB/i/btn/btn_donateCC_LG.gif" alt="PayPal this" title="PayPal – The safer, easier way to pay online!" border="0" />
</a>

or over bitcoin or bitcoin-cash with:

36JxRRDfRazLNqUV6NsywCw1q7TK38ukpC

or over ether with:

0x588Aa02De98B1Ef70afeDC3ec5290130a3E5e273

or over flattr: 
<a href="https://flattr.com/submit/auto?fid=r7vp62&url=https%3A%2F%2Fgithub.com%2Flightblueseas%2Fjcommons-lang" target="_blank">
<img src="http://button.flattr.com/flattr-badge-large.png" alt="Flattr this" title="Flattr this" border="0">
</a>

## Similar projects

 * [commons-lang](https://github.com/apache/commons-lang) Apache Commons Lang, a package of Java utility classes for the classes that are in java.lang's hierarchy, or are considered to be so standard as to justify existence in java.lang

## Credits

|Travis CI|
|:-:|
|![Travis CI](https://travis-ci.com/images/logos/TravisCI-Full-Color.png)|
|Many thanks to [Travis CI](https://travis-ci.org) for providing a free continuous integration service for open source projects.|
|![sonatype repository](https://avatars1.githubusercontent.com/u/33330803?s=200&v=4)|
|Many thanks to [sonatype repository](https://oss.sonatype.org) for providing a free maven repository service for open source projects.|
|![Coverage Status](https://coveralls.io/repos/github/lightblueseas/jcommons-lang/badge.svg)|
|Many thanks to [coveralls.io](https://coveralls.io) for providing a free code coverage for open source projects.|
|![Javadocs](http://www.javadoc.io/badge/de.alpharogroup/jcommons-lang.svg)|
|Many thanks to [javadoc.io](http://www.javadoc.io) for providing a free javadoc documentation for open source projects.|

