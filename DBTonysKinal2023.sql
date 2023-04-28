drop database if exists DBTonysKinal2023;
create database DBTonysKinal2023;
use DBTonysKinal2023;

create table EmployeeType
(
    idEmployeeType          int primary key not null auto_increment,
    descriptionEmployeeType varchar(150)    not null
);

create table Companies
(
    idCompany      int primary key not null auto_increment,
    nameCompany    varchar(150)    not null,
    addressCompany varchar(150)    not null,
    phoneCompany   varchar(150)    not null
);


create table DishType
(
    idDishType          int primary key not null auto_increment,
    descriptionDishType varchar(150)    not null
);

create table Products
(
    idProduct       int primary key not null auto_increment,
    productName     varchar(150)    not null,
    productQuantity int             not null
);

create table Employees
(
    idEmployee           int primary key not null auto_increment,
    employeeNumber       int             not null,
    firstNamesEmployee   varchar(150)    not null,
    lastNamesEmployee    varchar(150)    not null,
    addressEmployee      varchar(150)    not null,
    contactPhoneEmployee varchar(150)    not null,
    chefDegreeEmployee   int             not null,
    _idEmployeeType      int             not null,

    foreign key FK_Employees_EmployeeType (_idEmployeeType) references EmployeeType (idEmployeeType)
);

create table Services
(
    idService           int primary key not null auto_increment,
    dateService         date            not null,
    typeService         varchar(150)    not null,
    timeService         time            not null,
    locationService     varchar(150)    not null,
    contactPhoneService varchar(150)    not null,
    _idCompany          int             not null,

    foreign key FK_Services_Companies (_idCompany) references Companies (idCompany)
);

create table Budgets
(
    idBudget     int primary key not null auto_increment,
    requestDate  date            not null,
    budgetAmount decimal(10, 2)  not null,
    _idCompany   int             not null,

    foreign key FK_Budgets_Companies (_idCompany) references Companies (idCompany)
);

create table Dishes
(
    idDish          int primary key not null auto_increment,
    quantity        int             not null,
    dishName        varchar(150)    not null,
    dishDescription decimal(10, 2)  not null,
    dishPrice       decimal(10, 2)  not null,
    _idDishType     int             not null,

    foreign key FK_Dishes_DishType (_idDishType) references DishType (idDishType)
);

create table Products_has_Dishes
(
    _idProduct    int primary key not null,
    _idDish       int             not null,
    idProductDish int             not null,

    foreign key FK_Products_has_Dishes_Products (_idProduct) references Products (idProduct),
    foreign key FK_Products_has_Dishes_Dishes (_idDish) references Dishes (idDish)
);

create table Services_has_Dishes
(
    _idService    int primary key not null,
    _idDish       int             not null,
    idServiceDish int             not null,

    foreign key FK_Services_has_Dishes_Services (_idService) references Services (idService),
    foreign key FK_Services_has_Dishes_Dishes (_idDish) references Dishes (idDish)
);

create table Services_has_Employees
(
    _idService        int primary key not null,
    _idEmployee       int             not null,
    idServiceEmployee int             not null,
    eventDate         date            not null,
    eventTime         time            not null,
    eventLocation     varchar(150)    not null,

    foreign key FK_Services_has_Employees_Services (_idService) references Services (idService),
    foreign key FK_Services_has_Employees_Employees (_idEmployee) references Employees (idEmployee)
);


delimiter $$
create procedure sp_insert_EmployeeType(in sp_descriptionEmployeeType varchar(150))
begin
    insert into EmployeeType(descriptionEmployeeType)
    values (sp_descriptionEmployeeType);
end$$
delimiter ;


delimiter $$
create procedure sp_update_EmployeeType(in sp_idEmployeeType int, in sp_descriptionEmployeeType varchar(150))
begin
    update EmployeeType
    set idEmployeeType          = sp_idEmployeeType,
        descriptionEmployeeType = sp_descriptionEmployeeType
    where idEmployeeType = sp_idEmployeeType;
end$$
delimiter ;


delimiter $$
create procedure sp_delete_EmployeeType(in sp_idEmployeeType int)
begin
    delete
    from EmployeeType
    where idEmployeeType = sp_idEmployeeType;
end$$
delimiter ;


delimiter $$
create procedure sp_select_EmployeeType(in sp_idEmployeeType int)
begin
    select E.idEmployeeType, E.descriptionEmployeeType
    from EmployeeType E
    where idEmployeeType = sp_idEmployeeType;
end$$
delimiter ;


delimiter $$
create procedure sp_select_all_EmployeeType()
begin
    select E.idEmployeeType, E.descriptionEmployeeType from EmployeeType E;
end$$
delimiter ;

######################################################################################################################

delimiter $$
create procedure sp_insert_Companies(in sp_nameCompany varchar(150), in sp_addressCompany varchar(150),
                                     in sp_phoneCompany varchar(150))
begin
    insert into Companies(nameCompany, addressCompany, phoneCompany)
    values (sp_nameCompany, sp_addressCompany, sp_phoneCompany);
end$$
delimiter ;


delimiter $$
create procedure sp_update_Companies(in sp_idCompany int, in sp_nameCompany varchar(150),
                                     in sp_addressCompany varchar(150), in sp_phoneCompany varchar(150))
begin
    update Companies
    set idCompany      = sp_idCompany,
        nameCompany    = sp_nameCompany,
        addressCompany = sp_addressCompany,
        phoneCompany   = sp_phoneCompany
    where idCompany = sp_idCompany;
end$$
delimiter ;


delimiter $$
create procedure sp_delete_Companies(in sp_idCompany int)
begin
    delete
    from Companies
    where idCompany = sp_idCompany;
end$$
delimiter ;


delimiter $$
create procedure sp_select_Companies(in sp_idCompany int)
begin
    select C.idCompany, C.nameCompany, C.addressCompany, C.phoneCompany
    from Companies C
    where idCompany = sp_idCompany;
end$$
delimiter ;


delimiter $$
create procedure sp_select_all_Companies()
begin
    select C.idCompany, C.nameCompany, C.addressCompany, C.phoneCompany from Companies C;
end$$
delimiter ;

######################################################################################################################

delimiter $$
create procedure sp_insert_Employees(in sp_employeeNumber int, in sp_firstNamesEmployee varchar(150),
                                     in sp_lastNamesEmployee varchar(150), in sp_addressEmployee varchar(150),
                                     in sp_contactPhoneEmployee varchar(150), in sp_chefDegreeEmployee int,
                                     in sp__idEmployeeType int)
begin
    insert into Employees(employeeNumber, firstNamesEmployee, lastNamesEmployee, addressEmployee, contactPhoneEmployee,
                          chefDegreeEmployee, _idEmployeeType)
    values (sp_employeeNumber, sp_firstNamesEmployee, sp_lastNamesEmployee, sp_addressEmployee, sp_contactPhoneEmployee,
            sp_chefDegreeEmployee, sp__idEmployeeType);
end$$
delimiter ;


delimiter $$
create procedure sp_update_Employees(in sp_idEmployee int, in sp_employeeNumber int,
                                     in sp_firstNamesEmployee varchar(150), in sp_lastNamesEmployee varchar(150),
                                     in sp_addressEmployee varchar(150), in sp_contactPhoneEmployee varchar(150),
                                     in sp_chefDegreeEmployee int, in sp__idEmployeeType int)
begin
    update Employees
    set idEmployee           = sp_idEmployee,
        employeeNumber       = sp_employeeNumber,
        firstNamesEmployee   = sp_firstNamesEmployee,
        lastNamesEmployee    = sp_lastNamesEmployee,
        addressEmployee      = sp_addressEmployee,
        contactPhoneEmployee = sp_contactPhoneEmployee,
        chefDegreeEmployee   = sp_chefDegreeEmployee,
        _idEmployeeType      = sp__idEmployeeType
    where idEmployee = sp_idEmployee;
end$$
delimiter ;


delimiter $$
create procedure sp_delete_Employees(in sp_idEmployee int)
begin
    delete
    from Employees
    where idEmployee = sp_idEmployee;
end$$
delimiter ;


delimiter $$
create procedure sp_select_Employees(in sp_idEmployee int)
begin
    select E.idEmployee,
           E.employeeNumber,
           E.firstNamesEmployee,
           E.lastNamesEmployee,
           E.addressEmployee,
           E.contactPhoneEmployee,
           E.chefDegreeEmployee,
           E._idEmployeeType
    from Employees E
    where idEmployee = sp_idEmployee;
end$$
delimiter ;


delimiter $$
create procedure sp_select_all_Employees()
begin
    select E.idEmployee,
           E.employeeNumber,
           E.firstNamesEmployee,
           E.lastNamesEmployee,
           E.addressEmployee,
           E.contactPhoneEmployee,
           E.chefDegreeEmployee,
           E._idEmployeeType
    from Employees E;
end$$
delimiter ;

######################################################################################################################

delimiter $$
create procedure sp_insert_DishType(in sp_descriptionDishType varchar(150))
begin
    insert into DishType(descriptionDishType)
    values (sp_descriptionDishType);
end$$
delimiter ;


delimiter $$
create procedure sp_update_DishType(in sp_idDishType int, in sp_descriptionDishType varchar(150))
begin
    update DishType
    set idDishType          = sp_idDishType,
        descriptionDishType = sp_descriptionDishType
    where idDishType = sp_idDishType;
end$$
delimiter ;


delimiter $$
create procedure sp_delete_DishType(in sp_idDishType int)
begin
    delete
    from DishType
    where idDishType = sp_idDishType;
end$$
delimiter ;


delimiter $$
create procedure sp_select_DishType(in sp_idDishType int)
begin
    select D.idDishType, D.descriptionDishType
    from DishType D
    where idDishType = sp_idDishType;
end$$
delimiter ;


delimiter $$
create procedure sp_select_all_DishType()
begin
    select D.idDishType, D.descriptionDishType from DishType D;
end$$
delimiter ;

######################################################################################################################

delimiter $$
create procedure sp_insert_Products(in sp_productName varchar(150), in sp_productQuantity int)
begin
    insert into Products(productName, productQuantity)
    values (sp_productName, sp_productQuantity);
end$$
delimiter ;


delimiter $$
create procedure sp_update_Products(in sp_idProduct int, in sp_productName varchar(150), in sp_productQuantity int)
begin
    update Products
    set idProduct       = sp_idProduct,
        productName     = sp_productName,
        productQuantity = sp_productQuantity
    where idProduct = sp_idProduct;
end$$
delimiter ;


delimiter $$
create procedure sp_delete_Products(in sp_idProduct int)
begin
    delete
    from Products
    where idProduct = sp_idProduct;
end$$
delimiter ;


delimiter $$
create procedure sp_select_Products(in sp_idProduct int)
begin
    select P.idProduct, P.productName, P.productQuantity
    from Products P
    where idProduct = sp_idProduct;
end$$
delimiter ;


delimiter $$
create procedure sp_select_all_Products()
begin
    select P.idProduct, P.productName, P.productQuantity from Products P;
end$$
delimiter ;

######################################################################################################################

delimiter $$
create procedure sp_insert_Services(in sp_dateService date, in sp_typeService varchar(150), in sp_timeService time,
                                    in sp_locationService varchar(150), in sp_contactPhoneService varchar(150),
                                    in sp__idCompany int)
begin
    insert into Services(dateService, typeService, timeService, locationService, contactPhoneService, _idCompany)
    values (sp_dateService, sp_typeService, sp_timeService, sp_locationService, sp_contactPhoneService, sp__idCompany);
end$$
delimiter ;


delimiter $$
create procedure sp_update_Services(in sp_idService int, in sp_dateService date, in sp_typeService varchar(150),
                                    in sp_timeService time, in sp_locationService varchar(150),
                                    in sp_contactPhoneService varchar(150), in sp__idCompany int)
begin
    update Services
    set idService           = sp_idService,
        dateService         = sp_dateService,
        typeService         = sp_typeService,
        timeService         = sp_timeService,
        locationService     = sp_locationService,
        contactPhoneService = sp_contactPhoneService,
        _idCompany          = sp__idCompany
    where idService = sp_idService;
end$$
delimiter ;


delimiter $$
create procedure sp_delete_Services(in sp_idService int)
begin
    delete
    from Services
    where idService = sp_idService;
end$$
delimiter ;


delimiter $$
create procedure sp_select_Services(in sp_idService int)
begin
    select S.idService,
           S.dateService,
           S.typeService,
           S.timeService,
           S.locationService,
           S.contactPhoneService,
           S._idCompany
    from Services S
    where idService = sp_idService;
end$$
delimiter ;


delimiter $$
create procedure sp_select_all_Services()
begin
    select S.idService,
           S.dateService,
           S.typeService,
           S.timeService,
           S.locationService,
           S.contactPhoneService,
           S._idCompany
    from Services S;
end$$
delimiter ;

######################################################################################################################

delimiter $$
create procedure sp_insert_Budgets(in sp_requestDate date, in sp_budgetAmount decimal(10, 2), in sp__idCompany int)
begin
    insert into Budgets(requestDate, budgetAmount, _idCompany)
    values (sp_requestDate, sp_budgetAmount, sp__idCompany);
end$$
delimiter ;


delimiter $$
create procedure sp_update_Budgets(in sp_idBudget int, in sp_requestDate date, in sp_budgetAmount decimal(10, 2),
                                   in sp__idCompany int)
begin
    update Budgets
    set idBudget     = sp_idBudget,
        requestDate  = sp_requestDate,
        budgetAmount = sp_budgetAmount,
        _idCompany   = sp__idCompany
    where idBudget = sp_idBudget;
end$$
delimiter ;


delimiter $$
create procedure sp_delete_Budgets(in sp_idBudget int)
begin
    delete
    from Budgets
    where idBudget = sp_idBudget;
end$$
delimiter ;


delimiter $$
create procedure sp_select_Budgets(in sp_idBudget int)
begin
    select B.idBudget, B.requestDate, B.budgetAmount, B._idCompany
    from Budgets B
    where idBudget = sp_idBudget;
end$$
delimiter ;


delimiter $$
create procedure sp_select_all_Budgets()
begin
    select B.idBudget, B.requestDate, B.budgetAmount, B._idCompany from Budgets B;
end$$
delimiter ;

######################################################################################################################

delimiter $$
create procedure sp_insert_Dishes(in sp_quantity int, in sp_dishName varchar(150), in sp_dishDescription decimal(10, 2),
                                  in sp_dishPrice decimal(10, 2), in sp__idDishType int)
begin
    insert into Dishes(quantity, dishName, dishDescription, dishPrice, _idDishType)
    values (sp_quantity, sp_dishName, sp_dishDescription, sp_dishPrice, sp__idDishType);
end$$
delimiter ;


delimiter $$
create procedure sp_update_Dishes(in sp_idDish int, in sp_quantity int, in sp_dishName varchar(150),
                                  in sp_dishDescription decimal(10, 2), in sp_dishPrice decimal(10, 2),
                                  in sp__idDishType int)
begin
    update Dishes
    set idDish          = sp_idDish,
        quantity        = sp_quantity,
        dishName        = sp_dishName,
        dishDescription = sp_dishDescription,
        dishPrice       = sp_dishPrice,
        _idDishType     = sp__idDishType
    where idDish = sp_idDish;
end$$
delimiter ;


delimiter $$
create procedure sp_delete_Dishes(in sp_idDish int)
begin
    delete
    from Dishes
    where idDish = sp_idDish;
end$$
delimiter ;


delimiter $$
create procedure sp_select_Dishes(in sp_idDish int)
begin
    select D.idDish, D.quantity, D.dishName, D.dishDescription, D.dishPrice, D._idDishType
    from Dishes D
    where idDish = sp_idDish;
end$$
delimiter ;


delimiter $$
create procedure sp_select_all_Dishes()
begin
    select D.idDish, D.quantity, D.dishName, D.dishDescription, D.dishPrice, D._idDishType from Dishes D;
end$$
delimiter ;

######################################################################################################################

delimiter $$
create procedure sp_insert_Products_has_Dishes(in sp__idDish int, in sp_idProductDish int)
begin
    insert into Products_has_Dishes(_idDish, idProductDish)
    values (sp__idDish, sp_idProductDish);
end$$
delimiter ;


delimiter $$
create procedure sp_update_Products_has_Dishes(in sp__idProduct int, in sp__idDish int, in sp_idProductDish int)
begin
    update Products_has_Dishes
    set _idProduct    = sp__idProduct,
        _idDish       = sp__idDish,
        idProductDish = sp_idProductDish
    where _idProduct = sp__idProduct;
end$$
delimiter ;


delimiter $$
create procedure sp_delete_Products_has_Dishes(in sp__idProduct int)
begin
    delete
    from Products_has_Dishes
    where _idProduct = sp__idProduct;
end$$
delimiter ;


delimiter $$
create procedure sp_select_Products_has_Dishes(in sp__idProduct int)
begin
    select P._idProduct, P._idDish, P.idProductDish
    from Products_has_Dishes P
    where _idProduct = sp__idProduct;
end$$
delimiter ;


delimiter $$
create procedure sp_select_all_Products_has_Dishes()
begin
    select P._idProduct, P._idDish, P.idProductDish from Products_has_Dishes P;
end$$
delimiter ;

######################################################################################################################

delimiter $$
create procedure sp_insert_Services_has_Dishes(in sp__idDish int, in sp_idServiceDish int)
begin
    insert into Services_has_Dishes(_idDish, idServiceDish)
    values (sp__idDish, sp_idServiceDish);
end$$
delimiter ;


delimiter $$
create procedure sp_update_Services_has_Dishes(in sp__idService int, in sp__idDish int, in sp_idServiceDish int)
begin
    update Services_has_Dishes
    set _idService    = sp__idService,
        _idDish       = sp__idDish,
        idServiceDish = sp_idServiceDish
    where _idService = sp__idService;
end$$
delimiter ;


delimiter $$
create procedure sp_delete_Services_has_Dishes(in sp__idService int)
begin
    delete
    from Services_has_Dishes
    where _idService = sp__idService;
end$$
delimiter ;


delimiter $$
create procedure sp_select_Services_has_Dishes(in sp__idService int)
begin
    select S._idService, S._idDish, S.idServiceDish
    from Services_has_Dishes S
    where _idService = sp__idService;
end$$
delimiter ;


delimiter $$
create procedure sp_select_all_Services_has_Dishes()
begin
    select S._idService, S._idDish, S.idServiceDish from Services_has_Dishes S;
end$$
delimiter ;

######################################################################################################################

delimiter $$
create procedure sp_insert_Services_has_Employees(in sp__idEmployee int, in sp_idServiceEmployee int,
                                                  in sp_eventDate date, in sp_eventTime time,
                                                  in sp_eventLocation varchar(150))
begin
    insert into Services_has_Employees(_idEmployee, idServiceEmployee, eventDate, eventTime, eventLocation)
    values (sp__idEmployee, sp_idServiceEmployee, sp_eventDate, sp_eventTime, sp_eventLocation);
end$$
delimiter ;


delimiter $$
create procedure sp_update_Services_has_Employees(in sp__idService int, in sp__idEmployee int,
                                                  in sp_idServiceEmployee int, in sp_eventDate date,
                                                  in sp_eventTime time, in sp_eventLocation varchar(150))
begin
    update Services_has_Employees
    set _idService        = sp__idService,
        _idEmployee       = sp__idEmployee,
        idServiceEmployee = sp_idServiceEmployee,
        eventDate         = sp_eventDate,
        eventTime         = sp_eventTime,
        eventLocation     = sp_eventLocation
    where _idService = sp__idService;
end$$
delimiter ;


delimiter $$
create procedure sp_delete_Services_has_Employees(in sp__idService int)
begin
    delete
    from Services_has_Employees
    where _idService = sp__idService;
end$$
delimiter ;


delimiter $$
create procedure sp_select_Services_has_Employees(in sp__idService int)
begin
    select S._idService, S._idEmployee, S.idServiceEmployee, S.eventDate, S.eventTime, S.eventLocation
    from Services_has_Employees S
    where _idService = sp__idService;
end$$
delimiter ;


delimiter $$
create procedure sp_select_all_Services_has_Employees()
begin
    select S._idService, S._idEmployee, S.idServiceEmployee, S.eventDate, S.eventTime, S.eventLocation
    from Services_has_Employees S;
end$$
delimiter ;


# add tuples to tables without foreign keys

call sp_insert_DishType('Appetizer');
call sp_insert_DishType('Main Course');
call sp_insert_DishType('Dessert');

call sp_insert_Products('Chicken', 13);
call sp_insert_Products('Beef', 15);
call sp_insert_Products('Pork', 21);

call sp_insert_EmployeeType('Chef');
call sp_insert_EmployeeType('Waiter');
call sp_insert_EmployeeType('Bartender');

call sp_insert_Companies('Catering', 'Guatemala City', '12345678');
call sp_insert_Companies('Catering', 'Guatemala City', '12345678');
call sp_insert_Companies('Catering', 'Guatemala City', '12345678');
