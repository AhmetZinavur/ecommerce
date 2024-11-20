# Simple User Management System API: CRUD

---

##### This project was developed to create a CRUD (Create, Read, Update, Delete) API. The API provides basic CRUD operations for [user data model].

**Properties**

- **Create:** Create a new resource.
    
- **Read:** List or view details of available resources.
    
- **Update:** Update an existing resource.
    
- **Delete:** Deleting an existing resource.
    

**Used Technologies**

- **Java:** For API development.
    
- **Spring** **Boot:** Framework.
    
- **Hibernate:** ORM tool.
    
- **Postgresql:** Embedded database.
    
- **Building Tool:** Gradle
    
- **Postman:** For API testing.
    
---

### Installation

#### Clone source code:

```
git clone https://github.com/ahmetzinavur/simple-user-management-system.git
cd simple-user-management-system
```


#### Install dependencies:

Pull dependencies via your IDE or terminal.

##### Terminal:

```
For Bash:
cd simple-user-management-system
./gradlew clean
./gradlew build

For Windows:
cd simple-user-management-system
gradlew.bat bootRun
```

##### POST Create new user

```
localhost:8080/api/v1/create-user

Json:
{
    "name":"John",
    "email":"john@mail.com",
    "role":"USER"
}
```

##### GET Read all

```
localhost:8080/api/v1/users
```

##### GET Read by id 

```
localhost:8080/api/v1/user/id
```

##### PUT Update user

```
localhost:8080/api/v1/user/id

Json:
{
    "name":"John",
    "email":"john.updated@mail.com",
    "role":"USER"
}
```

##### DELETE Delete user

```
localhost:8080/api/v1/user/id
```

---

### Contributing Fork this project.

1- Create a new feature branch (feature/feature-name).

2- Commit your changes (git commit -am 'Feature addition description').

3- Push your branch (git push origin feature/feature-name).

4- Create a Pull Request.

---

### Licence

This project is licensed under the MIT License.