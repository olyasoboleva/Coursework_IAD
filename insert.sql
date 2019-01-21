INSERT into role values (1,'ROLE_ADMIN');
INSERT into role values (2,'ROLE_USER');
INSERT into role values (3,'ROLE_TRIBUTE');
INSERT INTO price values (1,'salary', 1000);
insert into status values (1, 'Наблюдатель', 1);
insert into status values (2, 'Трибут', 1);
insert into status values (3, 'Распорядитель', 1);

INSERT INTO weapon VALUES (1,'Нож','',50,10,null);
INSERT INTO weapon VALUES (2,'Трезубец','',50,40,null);
INSERT INTO weapon VALUES (3,'Лук','',70,200,null);
INSERT INTO weapon VALUES (4,'Копьё','',80,70,null);
INSERT INTO weapon VALUES (5,'Проволока','',30,5,null);
INSERT INTO weapon VALUES (6,'Леска','',20,5,null);

INSERT INTO skill VALUES (0,'Управление людьми','','Психологические',null);
INSERT INTO skill VALUES (1,'Знание боевых искусств','','Другие',null);
INSERT INTO skill VALUES (2,'Обращение с оружием','','Боевые',null);
INSERT INTO skill VALUES (3,'Знание электричества','','Боевые',null);
INSERT INTO skill VALUES (4,'Рыболовство','','Информационные',null);
INSERT INTO skill VALUES (5,'Знание энергетики','','Другие',null);
INSERT INTO skill VALUES (6,'Знание механики','','Информационные',null);
INSERT INTO skill VALUES (7,'Обращение с древесиной','','Информационные',null);
INSERT INTO skill VALUES (8,'Вязание узлов','','Информационные',null);
INSERT INTO skill VALUES (9,'Сила и выносливость','','Спортивные',null);
INSERT INTO skill VALUES (10,'Охота','','Информационные',null);
INSERT INTO skill VALUES (11,'Знание о культурных растениях','','Информационные',null);
INSERT INTO skill VALUES (12,'Разжигание костра','','Информационные',null);

INSERT INTO skill VALUES (13,'Владение ножом','','Боевые',1);
INSERT INTO skill VALUES (14,'Владение копьём','','Боевые',4);
INSERT INTO skill VALUES (15,'Владение луком и стрелами','','Боевые',3);
INSERT INTO skill VALUES (16,'Владение трезубцем','','Боевые',2);
INSERT INTO skill VALUES (17,'Маскировка','','Другие',null);
INSERT INTO skill VALUES (18,'Плавание','','Другие',null);

INSERT INTO district VALUES (0,'Капитолий','Управление',0);
INSERT INTO district VALUES (1,'Дистрикт 1','Роскошь',1);
INSERT INTO district VALUES (2,'Дистрикт 2','Оружие',2);
INSERT INTO district VALUES (3,'Дистрикт 3','Электроника',3);
INSERT INTO district VALUES (4,'Дистрикт 4','Рыболовство',4);
INSERT INTO district VALUES (5,'Дистрикт 5','Энергетика',5);
INSERT INTO district VALUES (6,'Дистрикт 6','Транспорт',6);
INSERT INTO district VALUES (7,'Дистрикт 7','Древесина',7);
INSERT INTO district VALUES (8,'Дистрикт 8','Текстиль',8);
INSERT INTO district VALUES (9,'Дистрикт 9','Сельское хозяйство',9);
INSERT INTO district VALUES (10,'Дистрикт 10','Животноводство',10);
INSERT INTO district VALUES (11,'Дистрикт 11','Сельское хозяйство',11);
INSERT INTO district VALUES (12,'Дистрикт 12','Добыча угля',12);

INSERT INTO location VALUES (0,'Лес',NULL);
INSERT INTO location VALUES (1,'Пустыня',NULL);
INSERT INTO location VALUES (2,'Снежная',NULL);
INSERT INTO location VALUES (3,'Тропический лес',NULL);
INSERT INTO location VALUES (4,'Водная',NULL);
INSERT INTO location VALUES (5,'Степь',NULL);
INSERT INTO location VALUES (6,'Тайга',NULL);

INSERT INTO hook VALUES (0,'Осы-убийцы',0,30);
INSERT INTO hook VALUES (1,'Водопад',4,50);
INSERT INTO hook VALUES (2,'Вулкан',0,50);
INSERT INTO hook VALUES (3,'Кровавый дождь',1,15);
INSERT INTO hook VALUES (4,'Капкан',3,40);
INSERT INTO hook VALUES (5,'Дикие обезьяны',3,40);

INSERT INTO shop VALUES (0,'Водяная трубка',10000,'Инструменты','Трубка для добычи воды из дерева',null,40,'Напиток');
INSERT INTO shop VALUES (1,'Булочки',20000,'Еда','Корзинка булочек',null,40,'Еда');
INSERT INTO shop VALUES (2,'Мазь от укусов',70000,'Лекарства','Небольшая балочка мази, спасает от укусов ос-убийц',null,40,'Лекарство');
INSERT INTO shop VALUES (3,'Бутылка воды',50000,'Напиток','Маленькая бутылка воды',null,40,'Напиток');
INSERT INTO shop VALUES (4,'Мазь для ожогов',70000,'Лекарства','Небольшая балочка мази, спасает от ожогов',null,40,'Лекарство');
INSERT INTO shop VALUES (5,'Нож',10000,'Инструменты','',null,40,'Еда');
INSERT INTO shop VALUES (6,'Сетка',10000,'Другое','Сетка для ловли рыбы',null,40,'Еда');
INSERT INTO shop VALUES (7,'Суп',20000,'Еда','Маленькая чашка с супом',null,40,'Еда');
INSERT INTO shop VALUES (8,'Стрелы',50000,'Инструменты','6 стрел',null,40,'Еда');
INSERT INTO shop VALUES (9,'Фляга',30000,'Другое','Небольшая фляга для воды',null,40,'Напиток');
INSERT INTO shop VALUES (10,'Йод',30000,'Лекарства','',null,40,'Лекарство');

INSERT INTO training VALUES (1,'Занятие с мечом',10,10,10,'Тренировка с мечами в зале',100,2);
INSERT INTO training VALUES (2,'Занятие с луком',15,10,10,'Стрельба из лука в лесу',100,6);
INSERT INTO training VALUES (3,'Занятие с булавой',19,10,10,'Тренировка с булавой на площадке',100,6);
INSERT INTO training VALUES (4,'Охота на животных',10,10,3,'Охота в лесу на мелких животных',100,6);

INSERT INTO price VALUES (2,'Ежедневный приз',1000);
INSERT INTO price VALUES (3,'Начальный баланс',2000);



