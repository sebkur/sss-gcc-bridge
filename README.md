# Port of libsss to Java using GCC-Bridge

This project is a port of [dsprenkels/sss](https://github.com/dsprenkels/sss)
to Java using [Renjin](https://github.com/bedatadriven/renjin)'s
[GCC-Bridge](https://github.com/bedatadriven/renjin/tree/master/tools/gcc-bridge).
It compiles the original C sources to JVM bytecode using GCC-Bridge
and provides convenient wrapper methods to access the generated methods.

## Requirements

The GCC-Bridge Maven plugin requires GCC 4.7 to be installed to compile C
sources. On Ubuntu GCC 4.7 was available on version 16.04 but is not available
on more recent versions and the GCC-Bridge currently relies on that specific
version of GCC.

To make the build work on a modern system, vagrant is utilized to set up
an old Ubuntu virtual machine and builds the project there. The build
artifacts are then platform independent and can be used on any system.

## Compiling

First you need to set up the build virtual machine using vagrant:

    vagrant up

After that completed you can ssh into the machine and build the project:

    vagrant ssh
    cd sss-gcc-bridge
    mvn install

Vagrant uses the project directory on your real local machine as a shared folder
so that the build results are available in your `target` directory locally.
