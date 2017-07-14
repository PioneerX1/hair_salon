<!-- Guest=# CREATE DATABASE hair_salon;
CREATE DATABASE
Guest=# \c hair_salon;
You are now connected to database "hair_salon" as user "Guest".
hair_salon=# CREATE TABLE stylists (id serial PRIMARY KEY, name varchar, wage int);
CREATE TABLE
hair_salon=# CREATE TABLE clients (id serial PRIMARY KEY, name varchar, age int, specialReq varchar, stylistId int);
CREATE TABLE
hair_salon=# CREATE DATABASE hair_salon_test WITH TEMPLATE hair_salon; -->
