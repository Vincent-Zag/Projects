# Stocks-API

simple Full-Stack, single-page application using Python, Flask with SQLAlchemy and more in the backend as well as JavaScript, React with Redux and CSS in the front end. used to scrape stock information from the web and store them in an S3 Bucket as well a MySQL RDS.

## Wiki

[Schema]([https://drawsql.app/teams/personal-928/diagrams/stocks-api/embed](https://drawsql.app/teams/personal-928/diagrams/stocks-api/embed))

[Asana Board](https://github.com/Jessie-Baron/Stocks-API/wiki/Asana-Board)

[Tableau DashBoard](https://public.tableau.com/app/profile/adriel.vincent.zagala/viz/Stocks_16862403340620/Dashboard1)

## Home Page

<img width="1432" alt="Screen Shot 2023-06-08 at 2 28 47 PM" src="https://github.com/Jessie-Baron/Stocks-API/assets/101578812/7f95db35-d560-4ec8-80e2-4875a8f60dd6">

## Search For a Stock

<img width="1410" alt="Screen Shot 2023-06-08 at 2 29 16 PM" src="https://github.com/Jessie-Baron/Stocks-API/assets/101578812/be43e2c4-53ba-4bf3-b37c-a0d1fa4df791">

## See Detailed Real-Time Information on Each Stock

<img width="711" alt="Screen Shot 2023-06-08 at 2 30 02 PM" src="https://github.com/Jessie-Baron/Stocks-API/assets/101578812/9b4876d0-a4e2-4b99-9d3c-25ace9e8a0c8">



## Getting started

Clone this repository (only this branch)

Install dependencies from docker file

    pipenv install -r requirements.txt
    
Copy the contents in the env-example file into a new .env file

Get into your pipenv, and run your Flask app

    pipenv shell
    flask run

To run the React App, checkout the README inside the react-app directory.
