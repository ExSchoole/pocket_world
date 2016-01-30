[ ![Codeship Status for ExSchoole/pocket_world](https://codeship.com/projects/708bc430-534d-0133-5a0a-2aa02ad0cd50/status?branch=master)](https://codeship.com/projects/108300)
# pocket_world
...
Follow this steps to try out our application:

    - first of all, install jdk for java 7, maven 3, latest version of mysql
    - create schema in postgres and grant all privileges for this schema to your postgres user
    - make copy of application.properties.example file in your %HOME_DIRECTORY% and name it application.properties
    - override properties for db.url, db.name and db.password
    - download source code from this repo, after this you have several ways to follow:
        - navigate to repo`s root folder, execute mvn clean install and copy war file from pocket_world_application/target directory to webapps directory in tomcat, start tomcat
        - execute mvn clean tomcat7:run from repo`s root folder (useful when you want to edit velocity templates)
        - if you are an intellij idea user you can use tomcat 7 configuration to run application (useful for debugging)
