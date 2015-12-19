REM This is the first to run. It will generate a generate a new maven project and one can fix the necessary things that needs to be changed

cd ..
rmdir simple-archetype /S /Q
call mvn archetype:generate "-DarchetypeGroupId=dk.emstar.spring" "-DarchetypeArtifactId=simple-archetype" "-DarchetypeVersion=1.0.0-SNAPSHOT" "-DgroupId=dk.emstar.spring" "-DartifactId=simple-archetype" "-Dversion=1.0.0-SNAPSHOT" "-DarchetypeCatalog=local"
copy create.bat simple-archetype
cd simple-archetype