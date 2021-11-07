CREATE TABLE IF NOT EXISTS Person (
    id integer identity primary key,
    name varchar(100),
    gender varchar(1),
    dateOfBirth date
);
INSERT INTO Person (id, name, gender, dateOfBirth) VALUES
    (1, 'Person_name1', 'M', parsedatetime('17-09-2012', 'dd-MM-yyyy')),
    (2, 'Person_name2', 'F', parsedatetime('17-09-2012', 'dd-MM-yyyy')),
    (3, 'Person_name3', 'M', parsedatetime('17-09-2012', 'dd-MM-yyyy')),
    (4, 'Person_name4', 'F', parsedatetime('17-09-2012', 'dd-MM-yyyy')),
    (5, 'Person_name5', 'F', parsedatetime('17-09-2012', 'dd-MM-yyyy'));
CREATE TABLE Items (
   ITEM_ID VARCHAR(10) PRIMARY KEY,
   DESCRIPTION VARCHAR(50) NOT NULL,
   INVENTORY_STATUS INTEGER NOT NULL
);
INSERT INTO Items (ITEM_ID, DESCRIPTION, INVENTORY_STATUS) VALUES
    ('Item_id0', 'Item_description0', 0),
    ('Item_id1', 'Item_description1', 0),
    ('Item_id2', 'Item_description2', 0),
    ('Item_id3', 'Item_description3', 0),
    ('Item_id4', 'Item_description4', 0);