CREATE TABLE patient (
    patientId int IDENTITY(1,1) NOT NULL,
    lastName varchar(40) NOT NULL,
    firstName varchar(40) NOT NULL,
    middleName varchar(40) NULL,
    gender char(1) NOT NULL,
    dateOfBirth date NOT NULL,
    phoneNumber varchar(15) UNIQUE NOT NULL,
    insuranceInformation varchar(255) NOT NULL,
	isDeleted bit NOT NULL DEFAULT 0
);

CREATE TABLE doctor (
    doctorId int IDENTITY(1,1) NOT NULL,
    lastName varchar(40) NOT NULL,
    firstName varchar(40) NOT NULL,
    middleName varchar(40) NOT NULL,
    dateOfBirth date NOT NULL,
    gender char(1) NOT NULL,
    education text NOT NULL,
    phoneNumber varchar(15) UNIQUE NOT NULL,
    emailAddress varchar(255) NULL
);

CREATE TABLE disease (
    diseaseId int IDENTITY(1,1) NOT NULL,
    diseaseName varchar(100) NOT NULL,
    diseaseCode varchar(10) NOT NULL,
    commonDiseaseDuration varchar(100) NULL
);

CREATE TABLE surgery (
    surgeryId int IDENTITY(1,1) NOT NULL,
    patientId int NOT NULL,
    scheduledDateTime datetime NULL,
    startTime datetime NULL,
    endTime datetime NULL,
    surgeryType varchar(100) NOT NULL,
    surgicalProcedureDescription text NULL,
    emergency bit NOT NULL
);

CREATE TABLE patientJournal (
    medicalHistoryNoteId int NOT NULL,
    patientId int NOT NULL,
    doctorId int NOT NULL,
    initialAdmission bit NOT NULL,
    discharge bit NOT NULL,
    patientStatus varchar(100) NOT NULL
);

CREATE TABLE patientDiseasesJournal (
    diseaseId int NOT NULL,
    patientId int NOT NULL,
    dispensaryAccounting bit NOT NULL
);

CREATE TABLE specialization (
    specializationId int IDENTITY(1,1) NOT NULL,
    specializationName varchar(100) NOT NULL
);

CREATE TABLE doctorSpecialization (
    doctorId int NOT NULL,
    specializationId int NOT NULL,
    yearsOfExperience int NOT NULL
);

CREATE TABLE doctorSurgeryJournal (
    doctorId int NOT NULL,
    surgeryId int NOT NULL,
    workingHours float NULL,
    scheduledWorkingHours float NULL
);

CREATE TABLE medicalHistoryNote (
    medicalHistoryNoteId int IDENTITY(1,1) NOT NULL,
    admissionDateTime datetime NOT NULL,
    anamnesis text NOT NULL
);

CREATE TABLE diseaseList (
    diseaseListId int IDENTITY(1,1) NOT NULL,
    diseaseId int NOT NULL,
    medicalHistoryNoteId int NOT NULL,
    treatmentId int NULL,
    resultsOfTreatment varchar(255) NOT NULL
);

CREATE TABLE medication (
    medicationId int IDENTITY(1,1) NOT NULL,
    medicationName varchar(100) NOT NULL,
    medicationForm varchar(100) NOT NULL,
    dosage varchar(100) NOT NULL,
    manufacturer varchar(100) NOT NULL,
    countryOfManufacture varchar(100) NOT NULL,
    dateOfManufacture date NOT NULL,
    expireDate date NOT NULL,
    isPrescription bit NOT NULL,
    price float NOT NULL,
    availableCount int NOT NULL
);

CREATE TABLE medicationList (
    medicationId int NOT NULL,
    surgeryId int NOT NULL,
    amount int NOT NULL
);

CREATE TABLE treatment (
    treatmentId  int IDENTITY(1,1) NOT NULL,
    doctorId int NOT NULL,
    doctorInstructions text NOT NULL,
    treatmentTime varchar(100) NOT NULL,
);

CREATE TABLE medicalProcedure (
    medicalProcedureId int IDENTITY(1,1) NOT NULL,
    medicalProcedureName varchar(100) NOT NULL,
    price float NOT NULL
);

CREATE TABLE recipeJournal(
    recipeId int NOT NULL,
    patientId int NOT NULL,
    medicalHistoryNoteId int NOT NULL
);

CREATE TABLE recipe (
    recipeId int IDENTITY(1,1) NOT NULL,
	medicationId int NOT NULL,
    expirationDate date NOT NULL
);

CREATE TABLE treatmentMedication (
	treatmentId int NOT NULL,
	medicationId int NOT NULL,
    amount int NOT NULL
);

CREATE TABLE treatmentMedicalProcedure (
    treatmentId int NOT NULL,
    medicalProcedureId int NOT NULL,
    amount int NOT NULL
);

CREATE TABLE doctorMedicalProcedure (
    medicalProcedureId int NOT NULL,
    doctorId int NOT NULL
);

CREATE TABLE users (
    userId int IDENTITY(1,1) NOT NULL,
    email varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    role varchar(20) NOT NULL
);

INSERT INTO patient (lastName, firstName, middleName, gender, dateOfBirth, phoneNumber, insuranceInformation)
VALUES
('Иванов', 'Иван', 'Иванович', 'М', '1990-05-15', '+71234567890', 'Информация по страховке 1'),
('Петрова', 'Анна', 'Ивановна', 'Ж', '1985-08-20', '+79876543210', 'Информация по страховке 2'),
('Сидоров', 'Петр', 'Алексеевич', 'М', '1978-03-10', '+75555555555', 'Информация по страховке 3'),
('Козлова', 'Мария', NULL, 'Ж', '1995-11-25', '+79999999999', 'Информация по страховке 4'),
('Николаев', 'Алексей', 'Игоревич', 'М', '2010-09-30', '+71111111111', 'Информация по страховке 5'),
('Смирнова', 'Ольга', 'Александровна', 'Ж', '1990-10-15', '+79111141111', 'Информация по страховке 6'),
('Павлов', 'Алексей', NULL, 'М', '1953-01-20', '+79222225222', 'Информация по страховке 7'),
('Кузнецова', 'Ирина', 'Ивановна', 'Ж', '1978-08-10', '+793335433333', 'Информация по страховке 8'),
('Ковалев', 'Сергей', 'Павлович', 'М', '1995-05-25', '+79444447744', 'Информация по страховке 9'),
('Морозова', 'Екатерина', 'Сергеевна', 'Ж', '1982-12-30', '+79555655555', 'Информация по страховке 10');


INSERT INTO doctor (lastName, firstName, middleName, dateOfBirth, gender, education, phoneNumber, emailAddress)
VALUES
('Смирнов', 'Александр', 'Петрович', '1970-12-05', 'М', 'Образование: Медицинское высшее образование\nВУЗ: Московский медицинский университет\nНаправление обучения: Общая медицина', '+71234567890', 'asmirnov@gmail.com'),
('Павлова', 'Елена', 'Анатольевна', '1983-07-15', 'Ж', 'Образование: Медицинское высшее образование\nВУЗ: Санкт-Петербургский медицинский университет\nНаправление обучения: Педиатрия', '+79876543210', 'epavlova@mail.ru'),
('Кузнецов', 'Иван', 'Васильевич', '1965-02-20', 'М', 'Образование: Медицинское высшее образование\nВУЗ: Киевский национальный медицинский университет\nНаправление обучения: Хирургия', '+75555555555', 'ikuznetsov'),
('Коваленко', 'Ольга', 'Владимировна', '1975-09-25', 'Ж', 'Образование: Медицинское высшее образование\nВУЗ: Медицинская академия им. Сеченова (Москва)\nНаправление обучения: Акушерство и гинекология', '+79999999999', 'okovalenko@yandex.ru'),
('Морозов', 'Дмитрий', 'Александрович', '1980-04-10', 'М', 'Образование: Медицинское высшее образование\nВУЗ: Санкт-Петербургская медицинская академия им. И. П. Павлова\nНаправление обучения: Офтальмология', '+71111111111', NULL),
('Козлов', 'Мария', 'Ивановна', '1985-04-25', 'Ж', 'Образование: Медицинское высшее образование\nВУЗ: Медицинская академия им. Сеченова (Москва)\nНаправление обучения: Гастроэнтерология', '+79456789012', 'kozlov@mail.ru'),
('Николаев', 'Петр', 'Сергеевич', '1988-09-30', 'М', 'Образование: Медицинское высшее образование\nВУЗ: Санкт-Петербургская медицинская академия им. И. П. Павлова\nНаправление обучения: Неврология', '+79567890623', 'nikolaev@mail.ru'),
('Петрова', 'Мария', 'Алексеевна', '1975-08-20', 'Ж', 'Образование: Медицинское высшее образование\nВУЗ: Санкт-Петербургский медицинский университет\nНаправление обучения: Онкология', '+79234567890', 'petrova@yandex.ru'),
('Сидоров', 'Иван', 'Петрович', '1972-11-10', 'М', 'Образование: Медицинское высшее образование\nВУЗ: Киевский национальный медицинский университет\nНаправление обучения: Ревматология', '+79345678901', NULL),
('Николаев', 'Павел', 'Сергеевич', '1980-04-15', 'М', 'Образование: Медицинское высшее образование\nВУЗ: Санкт-Петербургская медицинская академия им. И. П. Павлова\nНаправление обучения: Эндокринология', '+79567890123', 'nikolaev@hospital.com'),
('Павлов', 'Александр', 'Петрович', '1978-07-15', 'М', 'Образование: Медицинское высшее образование\nВУЗ: Санкт-Петербургский медицинский университет\nНаправление обучения: Кардиология', '+79789012345', 'pavlov@hospital.com'),
('Ковалев', 'Андрей', 'Иванович', '1982-09-25', 'М', 'Образование: Медицинское высшее образование\nВУЗ: Медицинский университет Минска\nНаправление обучения: Пульмонология', '+79901434567', 'kovalev@gmail.com'),
('Иванова', 'Анастасия', 'Сергеевна', '1986-03-25', 'Ж', 'Образование: Медицинское высшее образование\nВУЗ: Московский медицинский университет\nНаправление обучения: Физическая реабилитация и ЛФК', '+79998887766', 'ivanova@gmail.com');

INSERT INTO disease (diseaseName, diseaseCode, commonDiseaseDuration)
VALUES
('Бронхиальная астма', 'J45', NULL),
('Язва желудка', 'K25', NULL),
('Артроз', 'М15–М19', NULL),
('Эпилепсия', 'G40', NULL),
('Панкреатит', 'K85.9', NULL),
('Сколиоз', 'M41', NULL),
('ОРВИ', 'J06', '2-12 дней'),
('Грипп', 'J10', '2-10 дней'),
('Гастрит', 'K29', NULL),
('Пневмония', 'J18', '7-21 день'),
('Ангина', 'J03', '3-10 дней'),
('Артрит', 'M13', NULL),
('Сахарный диабет', 'E10-E14', NULL),
('Мигрень', 'G43', '4-72 часа'),
('Аппендицит', 'K35.9', '1-3 дня'),
('Гастрэктомия', 'Z93.1', NULL),
('Кесарево сечение', 'O82', NULL),
('Атеросклеротическая болезнь сердца', 'I25.1', NULL),
('Атеросклероз', 'I70.9', NULL),
('Рак груди', 'C50.9', NULL),
('Гипертония', 'I10-I15', NULL),
('Астма', 'J45', NULL),
('Гепатит B', 'K75', '2-26 недель'),
('Хроническая бронхит', 'J41', NULL),
('Ревматоидный артрит', 'M05', NULL),
('Диабет', 'E11', NULL),
('Панкреатит', 'K85', '5-10 дней'),
('Цистит', 'N30', '5-12 дней');

INSERT INTO medicalHistoryNote (admissionDateTime, anamnesis)
VALUES
('2024-03-08T14:15:00', 'Диагностирован хронический бронхит. Пациент жалуется на кашель, затрудненное дыхание и слабость. Принимает противокашлевые средства и противовоспалительные препараты.'),
('2024-03-10T15:45:00', 'Диагностирована язва желудка. Пациент жалуется на боли в эпигастральной области, изжогу после еды, тошноту и рвоту. Принимает препараты для снижения кислотности желудочного сока и антибиотики для лечения инфекции Helicobacter pylori.'),
('2024-03-11T09:00:00', 'Пациент страдает от артроза. Жалуется на боли и скованность в суставах, ухудшающуюся после физических нагрузок. Принимает противовоспалительные препараты и проходит курсы физиотерапии.'),
('2024-03-16T16:00:00', 'У пациента диагностирована эпилепсия. Имеются регулярные эпизоды судорог и потери сознания. Принимает антиэпилептические препараты для контроля эпилептических приступов.'),
('2024-03-17T08:15:00', 'Пациенту поставлен диагноз панкреатита. Жалуется на острые боли в эпигастральной области, тошноту, рвоту и общую слабость. Принимает противовоспалительные препараты и придерживается строгой диеты.'),
('2024-03-05T14:15:00', 'Диагностирован сколиоз. Пациент жалуется на боли в спине, деформацию позвоночника и ограничение подвижности. Принимает противовоспалительные препараты и проходит курсы физиотерапии и лфк.'),
('2024-03-08T14:15:00', 'Пациенту поставлен диагноз ОРВИ. Жалуется на насморк, кашель, боль в горле и общую слабость. Назначены антибиотики и средства от кашля.'),
('2024-03-15T14:00:00', 'Пациент здоров');

INSERT INTO patientJournal (medicalHistoryNoteId, patientId, doctorId, initialAdmission, discharge, patientStatus)
VALUES
(1, 1, 12, 1, 0, 'В процессе лечения'),
(2, 2, 6, 1, 0, 'В процессе лечения'),
(3, 3, 9, 1, 0, 'В процессе лечения'),
(4, 4, 3, 1, 0, 'В процессе лечения'),
(5, 5, 6, 1, 0, 'В процессе лечения'),
(6, 6, 3, 1, 0, 'В процессе лечения'),
(7, 7, 1, 1, 0, 'В процессе лечения'),
(8, 7, 1, 0, 1, 'Вылечен');


INSERT INTO patientDiseasesJournal (diseaseId, patientId, dispensaryAccounting)
VALUES
(1, 1, 1),
(2, 2, 1),
(3, 3, 1),
(4, 4, 1),
(5, 5, 0),
(6, 6, 0),
(7, 7, 0);

INSERT INTO medication (medicationName, medicationForm, dosage, manufacturer, countryOfManufacture, dateOfManufacture, expireDate, isPrescription, price, availableCount)
VALUES
('Амоксициллин', 'Таблетки', '500 мг', 'ФармХим', 'Россия', '2023-12-10', '2024-12-10', 1, 440, 30),
('Противокашлевый', 'Сироп', '10 мл', 'МедФарм', 'Россия', '2024-02-15', '2025-02-15', 1, 500, 100),
('Лидокаин', 'Инъекционный раствор', '5 мл', 'Аптека "Здоровье"', 'Россия', '2024-02-01', '2024-12-01', 1, 600, 50),
('Флуоресцеин', 'Порошок для приготовления раствора', '10 г', 'Медпрепараты', 'Россия', '2023-10-15', '2024-10-15', 0, 550, 100),
('Пропофол', 'Инъекционный раствор', '20 мл', 'ФармПроизводство', 'Россия', '2024-01-05', '2024-11-05', 1, 600, 20),
('Цефтриаксон', 'Порошок для приготовления раствора', '1 г', 'Медпоставки', 'Россия', '2023-11-20', '2024-11-20', 0, 320, 40),
('Ибупрофен', 'Таблетки', '200 мг', 'Медпрепараты', 'Россия', '2023-09-10', '2024-09-10', 0, 660, 25),
('Морфин', 'Инъекционный раствор', '10 мг', 'ФармХим', 'Россия', '2024-02-15', '2024-12-15', 1, 1250, 15),
('Левофлоксацин', 'Таблетки', '500 мг', 'Медпроизводство', 'Россия', '2023-08-20', '2024-08-20', 0, 1290, 35),
('Диклофенак', 'Таблетки', '50 мг', 'ФармПроизводство', 'Россия', '2023-11-01', '2024-11-01', 0, 870, 30),
('Вальпроевая кислота', 'Таблетки', '500 мг', 'Медпроизводство', 'Россия', '2023-12-20', '2024-12-20', 0, 950, 50),
('Метронидазол', 'Таблетки', '250 мг', 'ФармХим', 'Россия', '2023-10-05', '2024-10-05', 0, 1500, 40),
('Фентанил', 'Инъекционный раствор', '5 мг', 'Медпоставки', 'Россия', '2024-01-10', '2024-11-10', 1, 2300, 10),
('Меропенем', 'Порошок для приготовления раствора', '1 г', 'ФармПроизводство', 'Россия', '2023-09-25', '2024-09-25', 0, 1200, 60),
('Дексаметазон', 'Таблетки', '4 мг', 'ФармХим', 'Россия', '2023-11-15', '2024-11-15', 0, 1200, 45);

INSERT INTO treatment (doctorId, doctorInstructions, treatmentTime)
VALUES
(1, 'Принимать по 1 таблетке 1 раз в день после еды', '7 дней'),
(1, 'Принимать по 1 чайной ложке 2 раза в день после еды', '10 дней'),
(1, 'Принимать по 1 таблетке 1 раз в день после еды', '7 дней'),
(1, 'Принимать по 1 таблетке 1 раз в день после еды', '7 дней'),
(1, 'Принимать по 1 таблетке 1 раз в день после еды', '7 дней'),
(1, 'Принимать по 1 таблетке 1 раз в день после еды', '7 дней'),
(1, 'Принимать по 1 таблетке 1 раз в день после еды', '7 дней'),
(3, 'Электротерапия в течение 20 минут в день', '14 дней'),
(3, 'Упражнения Ларкина в течение 10 минут в день', '10 дней');

INSERT INTO treatmentMedication (treatmentId, medicationId, Amount)
VALUES
(1, 1, 1),
(2, 2, 1),
(3, 3, 1),
(4, 4, 1),
(5, 5, 1),
(6, 6, 1),
(7, 7, 1);

INSERT INTO diseaseList (diseaseId, medicalHistoryNoteId, treatmentId, resultsOfTreatment)
VALUES
(1, 1, 1, 'Улучшение после приема противокашлевых средств и противовоспалительных препаратов.'),
(2, 2, 2, 'Улучшение после приема препаратов для снижения кислотности желудочного сока и антибиотиков для лечения инфекции Helicobacter pylori.'),
(3, 3, 3, 'Улучшение после приема противовоспалительных препаратов и прохождения курсов физиотерапии.'),
(4, 4, 4, 'Контроль эпилептических приступов после приема антиэпилептических препаратов.'),
(5, 5, 5, 'Улучшение после приема противовоспалительных препаратов и соблюдения строгой диеты.'),
(6, 6, 6, 'Улучшение после приема противовоспалительных препаратов и прохождения курсов физиотерапии.'),
(7, 7, 2, 'Улучшение после приема средств от кашля.'),
(7, 7, 1, 'Улучшение после приема антибиотиков.');

-- ---
INSERT INTO surgery (patientId, scheduledDateTime, startTime, endTime, surgeryType, surgicalProcedureDescription, emergency)
VALUES
(1, '2024-03-09T14:15:00', '2024-03-09T12:15:00', '2024-03-09T16:15:00', 'Бронхоскопия', 'Исследование бронхов', 0),
(2, NULL, '2024-03-11T15:45:00', '2024-03-11T17:45:00', 'Лапароскопия', 'Исследование брюшной полости', 0),
(3, '2024-03-12T09:00:00', '2024-03-12T09:00:00', '2024-03-12T12:00:00', 'Артропластика', 'Восстановление сустава', 0),
(4, NULL, '2024-03-17T16:00:00', NULL, 'Эпилепсэктомия', 'Хирургическое лечение эпилепсии', 1),
(5, '2024-03-18T08:15:00', '2024-03-18T08:15:00', '2024-03-18T15:15:00', 'Панкреатэктомия', 'Удаление всей поджелудочной железы', 0);

INSERT INTO specialization (specializationName)
VALUES
('Терапевт'),
('Хирург'),
('Оториноларинголог'),
('Акушер-гинеколог'),
('Офтальмолог'),
('Гастроэнтеролог'),
('Невролог'),
('Онколог'),
('Ревматолог'),
('Эндокринолог'),
('Кардиолог'),
('Пульмонолог'),
('Врач ЛФК'),
('Ортопед'),
('Травматолог');

INSERT INTO medicalProcedure (medicalProcedureName, price)
VALUES
('Физиотерапия', 800),
('Массаж', 1000),
('ЛФК', 1500),
('ЭКГ', 2000),
('УЗИ', 2000);

INSERT INTO recipe (medicationId, expirationDate)
VALUES
(1, '2024-03-28'),
(13, '2024-03-30')

INSERT INTO medicationList (medicationId, surgeryId, amount)
VALUES
-- Бронхоскопия
(3, 1, 1), -- Лидокаин (Анестезия)
(4, 1, 1), -- Флуоресцеин (Контрастное средство)
(1, 1, 1), -- Амоксициллин (Антибиотик)

-- Лапароскопия
(5, 2, 1), -- Пропофол (Анестетик)
(6, 2, 1), -- Цефтриаксон (Антибиотик)
(7, 2, 1), -- Ибупрофен (Противовоспалительное средство)

-- Артропластика
(8, 3, 1), -- Морфин (Анальгетик)
(9, 3, 1), -- Левофлоксацин (Антибиотик)
(10, 3, 1), -- Диклофенак (Противовоспалительное средство)

-- Эпилепсэктомия
(5, 4, 1), -- Пропофол (Анестетик)
(11, 4, 1), -- Вальпроевая кислота (Антиконвульсант)
(12, 4, 1), -- Метронидазол (Антибиотик)

-- Панкреатэктомия
(13, 5, 1), -- Фентанил (Анальгетик)
(14, 5, 1), -- Меропенем (Антибиотик)
(15, 5, 1); -- Дексаметазон (Противовоспалительное средство)

INSERT INTO doctorSurgeryJournal (doctorId, surgeryId, workingHours)
VALUES
(12, 1, 8),
(6, 2, 7),
(9, 3, 8),
(3, 4, 10),
(3, 5, 6);

INSERT INTO recipeJournal (recipeId, patientId, medicalHistoryNoteId)
VALUES
(1, 7, 7),
(2, 4, 4);

INSERT INTO treatmentMedicalProcedure (treatmentId, medicalProcedureId, amount)
VALUES
(5, 1, 7),
(6, 3, 7);

INSERT INTO doctorMedicalProcedure (medicalProcedureId, doctorId)
VALUES
(1, 13),
(3, 13);

INSERT INTO doctorSpecialization (doctorId, specializationId, yearsOfExperience)
VALUES
(1, 1, 20),   -- Смирнов - Терапевт
(3, 2, 25),   -- Кузнецов - Хирург
(4, 4, 15),   -- Коваленко - Акушер-гинеколог
(5, 5, 18),   -- Морозов - Офтальмолог
(6, 6, 12),   -- Козлова - Гастроэнтеролог
(7, 7, 10),   -- Николаев (Петр) - Невролог
(8, 8, 22),   -- Петрова - Онколог
(9, 9, 28),   -- Сидоров - Ревматолог
(10, 10, 18), -- Николаев (Павел) - Эндокринолог
(11, 11, 15), -- Павлов (Александр) - Кардиолог
(12, 12, 20), -- Ковалев - Пульмонолог
(13, 13, 10); -- Иванова - ЛФК
