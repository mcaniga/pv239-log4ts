# Log4TS
Android application for logging of working hours to a company timesheet.

## Team
- Petr Kantek
- Michal Caniga

## Installation
- Create local.properties based on new-local.properties, specify path to sdk.dir

## Wireframes
- https://framer.com/projects/Android-projekt--oydTYd6uKgUnsvE5RyhN-1xtwt

## Technologies
- Firebase - backend, DB, auth
- Retrofit - REST

## Use Cases

### Authentication
- User can create an account
- User can log in to an existing account
- User can log out from an account
- User can redeem his password

### Namespaces
- User can create multiple namespaces, each namespace represents one company
- User can invite collaborators to a namespace
- User can be part of multiple namespaces
- User can remove collaborators to a namespace
- User can switch the current namespace to some available namespace, which he created or was invited to

### Projects
- User can create multiple projects in a namespace

### Logging
- User can log working hours, and associate the working hours with a project in a namespace which he is currently using
- User can change or delete his the entry about his working hours

### Reports
- User can see the working hours sum of each member from a current namespace
- User can see the individual entries for each member from a current namespace
- User can filter the reported working hours by start date, end date and project

### Navigation
- User can navigate to the Logging screen
- User can navigate to the Report screen
- User can navigate to the Namespaces screen

## Architecture
- Android clients <--REST--> Firebase backend