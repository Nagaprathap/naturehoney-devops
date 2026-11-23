# NatureHoney – DevOps practice project

This project is intentionally structured to practise **Git, GitHub, Maven, Nexus Repository and Apache Tomcat** with a small Java/JSP honey catalogue and admin product-management page.

## Application features
- Public product catalogue backed by MySQL
- Product detail page and WhatsApp order link
- Admin login
- Add/edit/delete products and upload product images
- Maven WAR packaging for Tomcat 10.1

## Environment
- JDK 17
- Maven 3.8+
- Git
- MySQL 8+
- Apache Tomcat 10.1 (Jakarta Servlet 6)
- Sonatype Nexus Repository 3 (Maven hosted release and snapshot repositories)

## A. Prepare the application

1. Configure MySQL using `database/schema.sql`. Change the example DB password first.
2. Set application environment variables in the environment that starts Tomcat:

```bash
export NH_DB_URL='jdbc:mysql://localhost:3306/naturehoney?useSSL=false&serverTimezone=UTC'
export NH_DB_USER='naturehoney'
export NH_DB_PASSWORD='YOUR_DATABASE_PASSWORD'
export NH_ADMIN_USER='admin'
export NH_ADMIN_PASSWORD='A_LONG_UNIQUE_PASSWORD'
```

3. Build and test:

```bash
mvn clean test
mvn clean package
ls -lh target/naturehoney.war
```

## B. Practise Git and GitHub

Create an empty GitHub repository named `NatureHoney` (or use your existing practice repository), then:

```bash
git init
git status
git add .
git commit -m "Initial NatureHoney web application"
git branch -M Dev
git remote add owc-ssh git@github.com:YOUR_USERNAME/NatureHoney.git
git push -u owc-ssh Dev
```

Practise a feature branch:

```bash
git switch -c feature/update-homepage
# edit src/main/webapp/index.jsp or assets/style.css
git diff
git add .
git commit -m "Update NatureHoney homepage"
git push -u owc-ssh feature/update-homepage
```

Then create a Pull Request on GitHub, merge it, and update local main:

```bash
git switch main
git pull --rebase owc-ssh Dev
```

Do not commit passwords, `~/.m2/settings.xml`, private keys, database dumps containing real data, or uploaded customer data.

## C. Practise Maven

Useful lifecycle commands:

```bash
mvn validate
mvn compile
mvn test
mvn package
mvn clean package
mvn dependency:tree
```

The output WAR is:

```text
target/naturehoney.war
```

## D. Configure Nexus

In Nexus Repository Manager, create two **Maven2 (hosted)** repositories:
- `maven-releases` – Version policy: Release; Deployment policy: Disable redeploy
- `maven-snapshots` – Version policy: Snapshot; Deployment policy: Allow redeploy

Update the Nexus host in the `<distributionManagement>` section of `pom.xml`. Keep the repository IDs exactly `nexus-releases` and `nexus-snapshots` unless you also update Maven settings.

Copy `maven-settings-example.xml` to `~/.m2/settings.xml`, replace placeholders with your Nexus credentials, and protect the file:

```bash
chmod 600 ~/.m2/settings.xml
```

For a release deployment, use a release version in `pom.xml`, for example `1.0.0`:

```bash
mvn clean deploy
```

For a snapshot deployment, change the project version to `1.0.1-SNAPSHOT`:

```bash
mvn clean deploy
```

Maven uploads the WAR and its POM to the matching hosted repository. Never put Nexus credentials in the project POM or GitHub.

## E. Deploy to Tomcat

For a first local deployment:

```bash
cp target/naturehoney.war /path/to/tomcat/webapps/
```

Or use the included helper:

```bash
TOMCAT_WEBAPPS=/path/to/tomcat/webapps ./scripts/deploy-to-tomcat.sh target/naturehoney.war
```

Restart Tomcat using your own service or customized startup script. Then browse:

```text
http://YOUR_SERVER:8080/naturehoney/
http://YOUR_SERVER:8080/naturehoney/admin/login.jsp
```

For a Nexus-to-Tomcat exercise, download the WAR from the Nexus UI (or use Maven dependency/plugin retrieval) and deploy that artifact, rather than copying directly from `target/`.

## F. Suggested hands-on practice order

1. Run the application locally or on EC2 and confirm the homepage loads.
2. Commit source code and push it to GitHub.
3. Make a change on a feature branch and merge a Pull Request.
4. Run Maven `clean test package`; inspect the WAR.
5. Publish version `1.0.0` to Nexus Releases.
6. Publish `1.0.1-SNAPSHOT` to Nexus Snapshots.
7. Download the release WAR from Nexus and deploy it to Tomcat.
8. Change the product page, publish `1.0.1`, and redeploy.
9. Practise rollback by redeploying the previous release WAR.

## Security and production notes

This is a learning starter, not a production-ready commerce platform. Before public use, add HTTPS, CSRF protection, secure password hashing, login throttling, server-side image content validation/re-encoding, persistent external image storage, and proper order/payment integration. The sample admin login uses environment-provided credentials for simplicity. The sample image upload writes into the deployed web application and may be lost on redeployment; use a persistent upload directory for a real deployment. Do not expose MySQL or Nexus to the public internet without appropriate access controls.
