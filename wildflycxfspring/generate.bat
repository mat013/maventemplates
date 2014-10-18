rmdir springcxf /S /Q
call mvn archetype:generate "-DarchetypeGroupId=dk.emstar.wildfly" "-DarchetypeArtifactId=springcxf-archetype" "-DarchetypeVersion=1.0.0-SNAPSHOT" "-DgroupId=dk.emstar.wildfly" "-DartifactId=springcxf" "-Dversion=1.0.0-SNAPSHOT" "-DarchetypeCatalog=local"
copy create.bat springcxf
cd springcxf