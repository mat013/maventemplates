call mvn clean install
copy applicationservice\target\${artifactId}.war %JBOSS_HOME%\standalone\deployments