# Deploying a Spring Boot App to Azure App Service with GitHub Actions

This guide will walk you through the process of automating the deployment of your Spring Boot application to Azure App Service using GitHub Actions. 

1. **Configure Azure App Service and Deployment Center:**

    - Go to the Azure Portal (https://portal.azure.com/).
    - Navigate to your Azure App Service where you want to deploy your Spring Boot application.
    - In the left-hand menu, under `Settings`, click on `Deployment Center`.
    - Choose the source as GitHub and follow the prompts to authenticate and select your repository.
    - .github/workflows/azure-deploy.yml`. This will define your GitHub Actions workflow.
### This is a yml file config using Gradle instead Maven to run project:
```shell
# Docs for the Azure Web Apps Deploy action: https://github.com/Azure/webapps-deploy
# More GitHub Actions for Azure: https://github.com/Azure/actions

name: Build and deploy JAR app to Azure Web App - spring-app-by-githubActions

on:
  push:
    branches:
      - devstage
  workflow_dispatch:

jobs:
  build:
    runs-on: ubuntu-latest

    steps:
      - uses: actions/checkout@v2

      - name: Set up Java version
        uses: actions/setup-java@v1
        with:
          java-version: '17'

      - name: Build with Gradle
        run: gradle clean build

      - name: Upload artifact for deployment job
        uses: actions/upload-artifact@v2
        with:
          name: java-app
          path: '${{ github.workspace }}/build/libs/spring-azure-githubAction.jar'

  deploy:
    runs-on: ubuntu-latest
    needs: build
    environment:
      name: 'Production'
      url: ${{ steps.deploy-to-webapp.outputs.webapp-url }}
    
    steps:
      - name: Download artifact from build job
        uses: actions/download-artifact@v2
        with:
          name: java-app

      - name: Deploy to Azure Web App
        id: deploy-to-webapp
        uses: azure/webapps-deploy@v2
        with:
          app-name: 'spring-app-by-githubActions'
          slot-name: 'Production'
          publish-profile: ${{ secrets.AZURE_WEBAPP_PUBLISH_PROFILE_**** }}
          package: '*.jar'
```

****Now, whenever you push changes to the `devstage` branch of your GitHub repository, GitHub Actions will automatically build and deploy your Spring Boot application to Azure App Service using the settings you configured in the Deployment Center in the Azure portal.****
