FROM registry.opensource.zalan.do/stups/openjdk:8u91-b14-1-22

MAINTAINER Zalando SE

COPY build/libs/api-space.jar /

CMD java $JAVA_OPTS $(java-dynamic-memory-opts) $(appdynamics-agent) -jar /api-space.jar

ADD scm-source.json /scm-source.json