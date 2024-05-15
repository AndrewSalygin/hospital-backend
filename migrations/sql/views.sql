-- 1
-- CREATE VIEW dbo.doctorV
-- AS  
-- SELECT dbo.doctor.lastName, dbo.doctor.firstName, dbo.doctor.middleName, dbo.doctor.gender, dbo.specialization.specializationName, dbo.doctorSpecialization.yearsOfExperience  
-- FROM dbo.doctor INNER JOIN  
--      dbo.doctorSpecialization ON dbo.doctor.doctorId = dbo.doctorSpecialization.doctorId INNER JOIN  
--      dbo.specialization ON dbo.doctorSpecialization.specializationId = dbo.specialization.specializationId;

-- SELECT * FROM doctorV;

-- 2
-- CREATE VIEW dbo.recipeV  
-- AS  
-- SELECT  dbo.recipe.recipeId, dbo.medicalHistoryNote.admissionDateTime, dbo.recipe.expirationDate, dbo.medication.medicationName, dbo.medication.medicationForm, dbo.medication.dosage,   
-- 		dbo.patient.lastName AS [patientLastName], dbo.patient.firstName AS [patientFirstName], dbo.patient.middleName AS [patientMiddleName], dbo.doctor.lastName AS [doctorLastName],   
-- 		dbo.doctor.firstName AS [doctorFirstName], dbo.doctor.middleName AS [doctorMiddleName]  
-- FROM	dbo.recipe INNER JOIN  
-- 		dbo.medication ON dbo.recipe.medicationId = dbo.medication.medicationId INNER JOIN  
-- 		dbo.recipeJournal ON dbo.recipe.recipeId = dbo.recipeJournal.recipeId INNER JOIN  
-- 		dbo.doctor INNER JOIN  
-- 		dbo.patientJournal ON dbo.doctor.doctorId = dbo.patientJournal.doctorId INNER JOIN  
-- 		dbo.medicalHistoryNote ON dbo.patientJournal.medicalHistoryNoteId = dbo.medicalHistoryNote.medicalHistoryNoteId INNER JOIN  
-- 		dbo.patient ON dbo.patientJournal.patientId = dbo.patient.patientId ON dbo.recipeJournal.medicalHistoryNoteId = dbo.medicalHistoryNote.medicalHistoryNoteId AND dbo.recipeJournal.patientId = dbo.patient.patientId;

-- SELECT * FROM recipeV;

-- 3
-- CREATE VIEW dbo.patientDiseaseV 
-- AS  
-- SELECT  dbo.patient.lastName, dbo.patient.firstName, dbo.patient.middleName, dbo.patient.gender, dbo.patient.dateOfBirth, dbo.patient.insuranceInformation, dbo.disease.diseaseCode, dbo.disease.diseaseName,   
--         dbo.patientDiseasesJournal.dispensaryAccounting  
-- FROM    dbo.disease INNER JOIN  
--         dbo.patientDiseasesJournal ON dbo.disease.diseaseId = dbo.patientDiseasesJournal.diseaseId INNER JOIN  
--         dbo.patient ON dbo.patientDiseasesJournal.patientId = dbo.patient.patientId;  

-- SELECT * FROM dbo.patientDiseaseV;

-- 4
-- CREATE VIEW dbo.doctorSpecializationInsertV
-- AS
-- SELECT *
-- FROM dbo.doctorSpecialization
-- WHERE yearsOfExperience >= 0
-- WITH CHECK OPTION;

-- INSERT INTO doctorSpecializationInsertV
-- VALUES (2, 14, -1);

-- INSERT INTO doctorSpecializationInsertV
-- VALUES (2, 14, 20);

-- SELECT * FROM doctorSpecializationInsertV;

-- 5
-- CREATE VIEW dbo.recipeInsertV
-- AS
-- SELECT *
-- FROM dbo.recipe
-- WHERE expirationDate >= GETDATE()
-- WITH CHECK OPTION;

-- SET IDENTITY_INSERT dbo.recipe ON;

-- INSERT INTO dbo.recipeInsertV (recipeId, medicationId, expirationDate)
-- VALUES (1, 5, '2022-03-28');

-- INSERT INTO dbo.recipeInsertV (recipeId, medicationId, expirationDate)
-- VALUES (1, 5, '2025-03-28');

-- SELECT * FROM dbo.recipeInsertV;

-- 6
-- CREATE VIEW dbo.patientInsertV
-- AS
-- SELECT *
-- FROM dbo.patient
-- WHERE gender IN ('М', 'Ж')
-- WITH CHECK OPTION;

-- INSERT INTO dbo.patientInsertV (lastName, firstName, middleName, gender, dateOfBirth, phoneNumber, insuranceInformation)
-- VALUES 
-- ('Иванов', 'Иван', 'Иванович', 'Х', '1993-03-15', '+71234547890', 'Информация по страховке 11');

-- INSERT INTO dbo.patientInsertV (lastName, firstName, middleName, gender, dateOfBirth, phoneNumber, insuranceInformation)
-- VALUES 
-- ('Иванов', 'Иван', 'Иванович', 'М', '1993-03-15', '+71234547890', 'Информация по страховке 11');

-- SELECT * FROM dbo.patientInsertV;