# jcommons-lang

<div align="center">

[![Build Status](https://travis-ci.org/lightblueseas/jcommons-lang.svg?branch=master)](https://travis-ci.org/lightblueseas/jcommons-lang)
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

		<!-- JCOMMONS-LANG version -->
		<jcommons-lang.version>4.32</jcommons-lang.version>

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

The only exception from the official guidelines are the following:

If the minor and patch version ends with a zero then the minor and the patch can be omitted.
 
For instance if the project version is '1.0.0' we will write '1'. So we omit the '.0.0'.

The same applies if only the patch version ends with a zero then the patch can be omitted.
 
For instance if the project version is '1.1.0' we will write '1.1'. So we omit the '.0'.

For more information on SemVer you can visit [semver.org](http://semver.org/).	

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

1Jzso5h7U82QCNmgxxSCya1yUK7UVcSXsW

or over ether with:

0xaB6EaE10F352268B0CA672Dd6e999C86344D49D8

or over flattr: 
<a href="https://flattr.com/submit/auto?fid=r7vp62&url=https%3A%2F%2Fgithub.com%2Flightblueseas%2Fjcommons-lang" target="_blank">
<img src="http://button.flattr.com/flattr-badge-large.png" alt="Flattr this" title="Flattr this" border="0">
</a>

## Credits

|Travis CI|
|:-:|
|![Travis CI](https://travis-ci.com/images/logos/TravisCI-Full-Color.png)|
|Many thanks to [Travis CI](https://travis-ci.org) for providing a free continuous integration service for open source projects.|

