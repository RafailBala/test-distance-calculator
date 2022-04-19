--liquibase formatted sql
--changeset techgeeknext:create-tables
Create Table City(
                     id int Primary Key,
                     Name varchar(40) Not Null Unique,
                     Latitude float Not Null,
                     Longitude float Not Null
);

Create Table Distance(
                         id int Primary Key,
                         id_from_city int Not Null,
                         id_to_city int Not Null,
                         distance float Not Null,
                         CONSTRAINT id_fromCity_fk FOREIGN KEY (id_from_city) REFERENCES City(id) ON UPDATE CASCADE ON DELETE CASCADE,
                         CONSTRAINT id_toCity_fk FOREIGN KEY (id_to_city) REFERENCES City(id) ON UPDATE CASCADE ON DELETE CASCADE);
