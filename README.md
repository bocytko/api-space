# API Space

[![Build Status](https://travis-ci.org/zalando-stups/api-space.svg?branch=master)](https://travis-ci.org/zalando-stups/api-space)

Git-based solution for versioning all API definitions and making them full-text searchable.

This is work in progress and not even on alpha level.

## Building

    $ gradle clean build
    $ scm-source
    $ docker build -t stups/api-space .

Or simply execute

    $ ./build-docker.sh
