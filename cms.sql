-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.1.52-community - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL Version:             10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for class_managment
CREATE DATABASE IF NOT EXISTS `class_managment` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `class_managment`;

-- Dumping structure for table class_managment.city
CREATE TABLE IF NOT EXISTS `city` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

-- Dumping data for table class_managment.city: ~9 rows (approximately)
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` (`id`, `city`) VALUES
	(1, 'Negombo'),
	(2, 'Colombo'),
	(4, 'Kadana'),
	(5, 'Kurunagala'),
	(6, 'Gampaha'),
	(7, 'Maharagama'),
	(8, 'seeduwa'),
	(9, 'Nugegoda'),
	(10, 'Homagama');
/*!40000 ALTER TABLE `city` ENABLE KEYS */;

-- Dumping structure for table class_managment.class
CREATE TABLE IF NOT EXISTS `class` (
  `id` varchar(25) NOT NULL,
  `subject_id` int(11) NOT NULL,
  `teacher_id` varchar(25) NOT NULL,
  `day_id` int(11) DEFAULT NULL,
  `desc` varchar(45) DEFAULT NULL,
  `start_time` time DEFAULT NULL,
  `end_time` time DEFAULT NULL,
  `addmision` double(11,2) DEFAULT NULL,
  `fee` double(11,2) DEFAULT NULL,
  `status` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_class_subject1` (`subject_id`),
  KEY `fk_class_teacher1` (`teacher_id`),
  KEY `FK_class_day` (`day_id`),
  CONSTRAINT `FK_class_day` FOREIGN KEY (`day_id`) REFERENCES `day` (`id`),
  CONSTRAINT `fk_class_subject1` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_class_teacher1` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table class_managment.class: ~5 rows (approximately)
/*!40000 ALTER TABLE `class` DISABLE KEYS */;
INSERT INTO `class` (`id`, `subject_id`, `teacher_id`, `day_id`, `desc`, `start_time`, `end_time`, `addmision`, `fee`, `status`) VALUES
	('CL-1', 1, 'TE-2', 5, '2027 ', '08:00:00', '12:00:00', 1000.00, 1500.00, 1),
	('CL-2', 3, 'TE-1', 2, '222', '03:00:00', '05:00:00', 2000.00, 800.00, 1),
	('CL-3', 2, 'TE-1', 2, 'sesssasasasa', '03:00:00', '05:00:00', 800.00, 1000.00, 1),
	('CL-4', 1, 'TE-1', 4, 'scscs', '03:16:00', '03:16:00', 12121.00, 12121.00, 1),
	('CL-5', 1, 'TE-1', 5, '34343434343', '03:18:00', '03:18:00', 121.00, 121.00, 1);
/*!40000 ALTER TABLE `class` ENABLE KEYS */;

-- Dumping structure for table class_managment.class_attendance
CREATE TABLE IF NOT EXISTS `class_attendance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `time` time DEFAULT NULL,
  `student_class_id` int(11) NOT NULL,
  `student_id` varchar(25) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_attendance_student_class1` (`student_class_id`),
  KEY `fk_class_attendance_student1` (`student_id`),
  CONSTRAINT `fk_attendance_student_class1` FOREIGN KEY (`student_class_id`) REFERENCES `student_class` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_class_attendance_student1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

-- Dumping data for table class_managment.class_attendance: ~11 rows (approximately)
/*!40000 ALTER TABLE `class_attendance` DISABLE KEYS */;
INSERT INTO `class_attendance` (`id`, `date`, `time`, `student_class_id`, `student_id`) VALUES
	(1, '2020-04-23', '12:49:49', 24, 'ST-2'),
	(3, '2020-04-23', '14:01:13', 19, 'ST-3'),
	(4, '2020-04-23', '14:17:56', 20, 'ST-2'),
	(5, '2020-04-23', '14:27:16', 16, 'ST-5'),
	(6, '2020-04-23', '14:53:17', 25, 'ST-6'),
	(7, '2020-04-28', '14:02:40', 13, 'ST-2'),
	(8, '2020-04-29', '06:38:00', 20, 'ST-2'),
	(9, '2020-04-30', '19:06:10', 20, 'ST-2'),
	(10, '2020-05-01', '15:47:00', 18, 'ST-5'),
	(11, '2020-05-01', '17:32:10', 20, 'ST-2'),
	(12, '2020-05-01', '17:32:31', 19, 'ST-3');
/*!40000 ALTER TABLE `class_attendance` ENABLE KEYS */;

-- Dumping structure for table class_managment.class_cancle
CREATE TABLE IF NOT EXISTS `class_cancle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reason` text NOT NULL,
  `date` datetime NOT NULL,
  `class_id` varchar(25) NOT NULL DEFAULT '0',
  `staff_id` varchar(25) NOT NULL DEFAULT '0',
  `inform` int(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_class_cancle_class` (`class_id`),
  KEY `FK_class_cancle_staff` (`staff_id`),
  CONSTRAINT `FK_class_cancle_class` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`),
  CONSTRAINT `FK_class_cancle_staff` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Dumping data for table class_managment.class_cancle: ~1 rows (approximately)
/*!40000 ALTER TABLE `class_cancle` DISABLE KEYS */;
INSERT INTO `class_cancle` (`id`, `reason`, `date`, `class_id`, `staff_id`, `inform`) VALUES
	(1, 'ee', '2020-05-03 08:00:02', 'CL-2', 'EM-1', 1);
/*!40000 ALTER TABLE `class_cancle` ENABLE KEYS */;

-- Dumping structure for table class_managment.class_leaving
CREATE TABLE IF NOT EXISTS `class_leaving` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `time` time DEFAULT NULL,
  `reason` text,
  `student_id` varchar(25) NOT NULL,
  `student_class_id` int(11) NOT NULL,
  `staff_id` varchar(25) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_leving_student1` (`student_id`),
  KEY `fk_leving_student_class1` (`student_class_id`),
  KEY `fk_class_leaving_staff1` (`staff_id`),
  CONSTRAINT `fk_class_leaving_staff1` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_leving_student1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_leving_student_class1` FOREIGN KEY (`student_class_id`) REFERENCES `student_class` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Dumping data for table class_managment.class_leaving: ~5 rows (approximately)
/*!40000 ALTER TABLE `class_leaving` DISABLE KEYS */;
INSERT INTO `class_leaving` (`id`, `date`, `time`, `reason`, `student_id`, `student_class_id`, `staff_id`) VALUES
	(1, '2020-04-28', '10:15:30', 'asas', 'ST-7', 12, 'EM-2'),
	(2, '2020-04-28', '10:22:19', 'asasas\n', 'ST-2', 12, 'EM-2'),
	(3, '2020-04-28', '13:49:10', 'zxzxz', 'ST-3', 14, 'EM-2'),
	(4, '2020-04-28', '13:59:05', 'hjkhj', 'ST-2', 13, 'EM-2'),
	(5, '2020-04-29', '06:18:39', 'jkjij', 'ST-3', 19, 'EM-2');
/*!40000 ALTER TABLE `class_leaving` ENABLE KEYS */;

-- Dumping structure for table class_managment.class_payment
CREATE TABLE IF NOT EXISTS `class_payment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `amount` double(11,3) DEFAULT NULL,
  `teacher_id` varchar(25) NOT NULL,
  `staff_id` varchar(25) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_class_payment_teacher1` (`teacher_id`),
  KEY `fk_class_payment_staff1` (`staff_id`),
  CONSTRAINT `fk_class_payment_staff1` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_class_payment_teacher1` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

-- Dumping data for table class_managment.class_payment: ~2 rows (approximately)
/*!40000 ALTER TABLE `class_payment` DISABLE KEYS */;
INSERT INTO `class_payment` (`id`, `date`, `amount`, `teacher_id`, `staff_id`) VALUES
	(10, '2020-05-01 10:27:45', 4000.000, 'TE-2', 'EM-1'),
	(11, '2020-04-30 10:44:21', 5000.000, 'TE-2', 'EM-1');
/*!40000 ALTER TABLE `class_payment` ENABLE KEYS */;

-- Dumping structure for table class_managment.class_payment_classes
CREATE TABLE IF NOT EXISTS `class_payment_classes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `total` double(11,2) NOT NULL DEFAULT '0.00',
  `students` int(11) NOT NULL DEFAULT '0',
  `student_class_card_type_id` int(11) NOT NULL DEFAULT '0',
  `class_payment_id` int(11) NOT NULL DEFAULT '0',
  `class_id` varchar(25) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_class_payment_classes_student_class_card_type` (`student_class_card_type_id`),
  KEY `FK_class_payment_classes_class_payment` (`class_payment_id`),
  KEY `FK_class_payment_classes_class` (`class_id`),
  CONSTRAINT `FK_class_payment_classes_class` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`),
  CONSTRAINT `FK_class_payment_classes_class_payment` FOREIGN KEY (`class_payment_id`) REFERENCES `class_payment` (`id`),
  CONSTRAINT `FK_class_payment_classes_student_class_card_type` FOREIGN KEY (`student_class_card_type_id`) REFERENCES `student_class_card_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;

-- Dumping data for table class_managment.class_payment_classes: ~6 rows (approximately)
/*!40000 ALTER TABLE `class_payment_classes` DISABLE KEYS */;
INSERT INTO `class_payment_classes` (`id`, `total`, `students`, `student_class_card_type_id`, `class_payment_id`, `class_id`) VALUES
	(23, 3150.00, 3, 3, 10, 'CL-1'),
	(24, 1050.00, 2, 3, 10, 'CL-1'),
	(25, 0.00, 1, 3, 10, 'CL-1'),
	(26, 3150.00, 3, 3, 11, 'CL-1'),
	(27, 1050.00, 2, 3, 11, 'CL-1'),
	(28, 0.00, 1, 3, 11, 'CL-1');
/*!40000 ALTER TABLE `class_payment_classes` ENABLE KEYS */;

-- Dumping structure for table class_managment.day
CREATE TABLE IF NOT EXISTS `day` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `day` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- Dumping data for table class_managment.day: ~7 rows (approximately)
/*!40000 ALTER TABLE `day` DISABLE KEYS */;
INSERT INTO `day` (`id`, `day`) VALUES
	(1, 'Monday'),
	(2, 'Tuesday'),
	(3, 'Wednesday'),
	(4, 'Thursday'),
	(5, 'Friday'),
	(6, 'Saturday'),
	(7, 'Sunday');
/*!40000 ALTER TABLE `day` ENABLE KEYS */;

-- Dumping structure for table class_managment.exam
CREATE TABLE IF NOT EXISTS `exam` (
  `id` varchar(25) NOT NULL,
  `subject_id` int(11) NOT NULL,
  `desc` text,
  `date` date DEFAULT NULL,
  `start` time DEFAULT NULL,
  `end` time DEFAULT NULL,
  `status` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_exam_subject1` (`subject_id`),
  CONSTRAINT `fk_exam_subject1` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table class_managment.exam: ~6 rows (approximately)
/*!40000 ALTER TABLE `exam` DISABLE KEYS */;
INSERT INTO `exam` (`id`, `subject_id`, `desc`, `date`, `start`, `end`, `status`) VALUES
	('EX-1', 4, '2021 OL', '2020-05-01', '16:21:00', '16:29:01', 1),
	('EX-2', 1, '2020 AL', '2020-04-30', '08:00:00', '10:30:00', 1),
	('EX-3', 1, '2021 AL', '2020-05-29', '02:00:00', '05:00:00', 1),
	('EX-4', 6, 'Student Knwoladge test', '2020-04-28', '05:12:00', '05:12:00', 1),
	('EX-5', 1, 'test paper', '2020-04-23', '02:00:00', '05:00:00', 1),
	('EX-6', 1, 'qeqweqwew', '2020-08-13', '02:00:00', '05:00:00', 1);
/*!40000 ALTER TABLE `exam` ENABLE KEYS */;

-- Dumping structure for table class_managment.exam_attendance
CREATE TABLE IF NOT EXISTS `exam_attendance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `exam_id` varchar(25) NOT NULL DEFAULT '',
  `student_id` varchar(25) NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_exam_attendance_exam_date1` (`exam_id`),
  KEY `fk_exam_attendance_student1` (`student_id`),
  CONSTRAINT `FK_exam_attendance_exam` FOREIGN KEY (`exam_id`) REFERENCES `exam` (`id`),
  CONSTRAINT `fk_exam_attendance_student1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- Dumping data for table class_managment.exam_attendance: ~4 rows (approximately)
/*!40000 ALTER TABLE `exam_attendance` DISABLE KEYS */;
INSERT INTO `exam_attendance` (`id`, `exam_id`, `student_id`, `date`, `time`) VALUES
	(3, 'EX-2', 'ST-7', '2020-05-02', '00:49:12'),
	(4, 'EX-2', 'ST-3', '2020-05-02', '10:48:47'),
	(5, 'EX-3', 'ST-2', '2020-05-02', '10:49:14'),
	(6, 'EX-3', 'ST-12', '2020-05-02', '10:49:24');
/*!40000 ALTER TABLE `exam_attendance` ENABLE KEYS */;

-- Dumping structure for table class_managment.exam_result
CREATE TABLE IF NOT EXISTS `exam_result` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `marks` double DEFAULT NULL,
  `grade` varchar(15) DEFAULT NULL,
  `exam_attendance_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_exam_result_exam_attendance1` (`exam_attendance_id`),
  CONSTRAINT `FK_exam_result_exam_attendance` FOREIGN KEY (`exam_attendance_id`) REFERENCES `exam_attendance` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Dumping data for table class_managment.exam_result: ~1 rows (approximately)
/*!40000 ALTER TABLE `exam_result` DISABLE KEYS */;
INSERT INTO `exam_result` (`id`, `marks`, `grade`, `exam_attendance_id`) VALUES
	(3, 23, 'W', 4);
/*!40000 ALTER TABLE `exam_result` ENABLE KEYS */;

-- Dumping structure for table class_managment.fees
CREATE TABLE IF NOT EXISTS `fees` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `total` double(11,2) DEFAULT NULL,
  `payment` double(11,2) DEFAULT NULL,
  `staff_id` varchar(25) NOT NULL,
  `student_id` varchar(25) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_fees_staff1` (`staff_id`),
  KEY `FK_fees_student` (`student_id`),
  CONSTRAINT `fk_fees_staff1` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_fees_student` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=latin1;

-- Dumping data for table class_managment.fees: ~50 rows (approximately)
/*!40000 ALTER TABLE `fees` DISABLE KEYS */;
INSERT INTO `fees` (`id`, `date`, `total`, `payment`, `staff_id`, `student_id`) VALUES
	(9, '2020-04-20 20:26:35', 4600.00, 5000.00, 'EM-1', 'ST-2'),
	(10, '2020-05-20 20:27:14', 2800.00, 3000.00, 'EM-1', 'ST-3'),
	(11, '2020-05-20 20:51:27', 2800.00, 3000.00, 'EM-1', 'ST-4'),
	(12, '2020-04-20 20:52:12', 2500.00, 3000.00, 'EM-1', 'ST-5'),
	(13, '2020-04-20 22:18:22', 2800.00, 3000.00, 'EM-1', 'ST-6'),
	(14, '2020-05-20 22:21:15', 2800.00, 3000.00, 'EM-1', 'ST-7'),
	(17, '2020-04-22 09:13:01', 1800.00, 2000.00, 'EM-1', 'ST-3'),
	(18, '2020-04-22 10:13:05', 3400.00, 3500.00, 'EM-1', 'ST-2'),
	(19, '2020-04-22 10:42:34', 5200.00, 5500.00, 'EM-1', 'ST-8'),
	(20, '2020-04-22 10:45:20', 1800.00, 2000.00, 'EM-1', 'ST-6'),
	(21, '2020-04-23 09:52:52', 2400.00, 2500.00, 'EM-1', 'ST-9'),
	(23, '2020-04-25 11:06:38', 2500.00, 3000.00, 'EM-1', 'ST-10'),
	(24, '2020-04-25 12:20:56', 2400.00, 2500.00, 'EM-1', 'ST-11'),
	(25, '2020-04-25 12:25:27', 2000.00, 2000.00, 'EM-1', 'ST-12'),
	(26, '2020-04-25 23:29:27', 2000.00, 2000.00, 'EM-1', 'ST-13'),
	(28, '2020-04-26 16:10:52', 1000.00, 1000.00, 'EM-1', 'st-2'),
	(29, '2020-04-26 16:26:22', 1900.00, 2000.00, 'EM-1', 'st-3'),
	(30, '2020-04-28 22:19:18', 1800.00, 2000.00, 'EM-1', 'ST-14'),
	(31, '2020-04-28 22:20:02', 400.00, 500.00, 'EM-1', 'st-8'),
	(32, '2020-04-28 22:21:05', 800.00, 1000.00, 'EM-1', 'st-4'),
	(33, '2020-04-28 22:22:40', 800.00, 1000.00, 'EM-1', 'st-4'),
	(34, '2020-04-28 22:23:51', 800.00, 1000.00, 'EM-1', 'st-4'),
	(35, '2020-04-28 22:32:43', 2150.00, 2200.00, 'EM-1', 'st-2'),
	(36, '2020-04-28 22:33:33', 1750.00, 2000.00, 'EM-1', 'st-2'),
	(37, '2020-04-29 00:31:52', 2150.00, 2200.00, 'EM-1', 'st-2'),
	(38, '2020-04-29 00:33:05', 1000.00, 1000.00, 'EM-1', 'st-2'),
	(39, '2020-04-29 00:35:17', 2150.00, 2200.00, 'EM-1', 'st-2'),
	(40, '2020-04-29 00:37:04', 1500.00, 2000.00, 'EM-1', 'st-3'),
	(41, '2020-04-29 00:39:16', 400.00, 500.00, 'EM-1', 'st-2'),
	(42, '2020-04-29 00:41:12', 750.00, 1000.00, 'EM-1', 'st-2'),
	(43, '2020-04-29 00:48:50', 2150.00, 2200.00, 'EM-1', 'st-2'),
	(44, '2020-05-29 00:55:46', 2150.00, 2200.00, 'EM-1', 'st-2'),
	(45, '2020-04-29 06:26:01', 1000.00, 1000.00, 'EM-1', 'st-2'),
	(46, '2020-05-29 07:53:07', 1500.00, 2000.00, 'EM-1', 'st-3'),
	(47, '2020-04-29 10:33:36', 800.00, 1000.00, 'EM-1', 'ST-9'),
	(48, '2020-05-29 10:33:37', 800.00, 1000.00, 'EM-1', 'ST-9'),
	(49, '2020-04-29 10:33:39', 800.00, 1000.00, 'EM-1', 'ST-9'),
	(50, '2020-04-29 10:33:45', 800.00, 1000.00, 'EM-1', 'ST-9'),
	(51, '2020-04-29 10:34:35', 1300.00, 1500.00, 'EM-1', 'ST-3'),
	(52, '2020-04-30 10:50:48', 1400.00, 1500.00, 'EM-1', 'ST-8'),
	(53, '2020-04-30 20:40:42', 2400.00, 2500.00, 'EM-1', 'st-3'),
	(54, '2020-04-30 20:46:28', 2150.00, 2200.00, 'EM-1', 'st-2'),
	(55, '2020-04-30 20:48:37', 1000.00, 1000.00, 'EM-1', 'st-2'),
	(56, '2020-04-30 20:51:51', 1000.00, 1000.00, 'EM-1', 'st-2'),
	(57, '2020-04-30 20:53:28', 1000.00, 1000.00, 'EM-1', 'st-2'),
	(58, '2020-04-30 20:57:31', 1000.00, 1000.00, 'EM-1', 'st-2'),
	(59, '2020-04-30 20:58:59', 1000.00, 1000.00, 'EM-1', 'st-2'),
	(60, '2020-05-02 14:57:57', 500.00, 500.00, 'EM-1', 'st-3'),
	(61, '2020-05-10 09:43:25', 400.00, 500.00, 'EM-1', 'st-3'),
	(64, '2020-05-10 17:26:30', 1000.00, 1000.00, 'EM-1', 'st-2');
/*!40000 ALTER TABLE `fees` ENABLE KEYS */;

-- Dumping structure for table class_managment.grade
CREATE TABLE IF NOT EXISTS `grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `grade` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- Dumping data for table class_managment.grade: ~8 rows (approximately)
/*!40000 ALTER TABLE `grade` DISABLE KEYS */;
INSERT INTO `grade` (`id`, `grade`) VALUES
	(1, 'Grade 6'),
	(2, 'Grade 7'),
	(3, 'Grade 8'),
	(4, 'Grade 9'),
	(5, 'Grade 10'),
	(6, 'Grade 11'),
	(7, 'Grade 12'),
	(8, 'Grade 13');
/*!40000 ALTER TABLE `grade` ENABLE KEYS */;

-- Dumping structure for table class_managment.institute
CREATE TABLE IF NOT EXISTS `institute` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text,
  `address` text,
  `phone1` varchar(15) DEFAULT NULL,
  `phone2` varchar(15) DEFAULT NULL,
  `website` text,
  `fax` varchar(15) DEFAULT NULL,
  `thank_msg` text,
  `addmision` double DEFAULT NULL,
  `to_institute` int(11) DEFAULT NULL,
  `path` text,
  `noti_pay` int(2) DEFAULT NULL,
  `print_after_pay` int(2) DEFAULT NULL,
  `noti_leave` int(2) DEFAULT NULL,
  `logo_invoice` int(2) DEFAULT NULL,
  `noti_exam_results` int(2) DEFAULT NULL,
  `print_2_invoice` int(2) DEFAULT NULL,
  `noti_register` int(2) DEFAULT NULL,
  `noti_class_cancel` int(2) DEFAULT NULL,
  `noti_user_login` int(2) DEFAULT NULL,
  `print_barcode` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Dumping data for table class_managment.institute: ~1 rows (approximately)
/*!40000 ALTER TABLE `institute` DISABLE KEYS */;
INSERT INTO `institute` (`id`, `name`, `address`, `phone1`, `phone2`, `website`, `fax`, `thank_msg`, `addmision`, `to_institute`, `path`, `noti_pay`, `print_after_pay`, `noti_leave`, `logo_invoice`, `noti_exam_results`, `print_2_invoice`, `noti_register`, `noti_class_cancel`, `noti_user_login`, `print_barcode`) VALUES
	(1, 'CMS srilanka', 'NO 69, Goluwapokuna, Bolawalana, Negombo', '0718807480', '0312231347', 'abhispeiris.com', '0312231347', 'THANK YOU', 0, 0, 'src/Resources/images/institute/1589004238453.png', 0, 1, 1, 1, 1, 0, 1, 1, 1, 1);
/*!40000 ALTER TABLE `institute` ENABLE KEYS */;

-- Dumping structure for table class_managment.month
CREATE TABLE IF NOT EXISTS `month` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `month` varchar(55) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

-- Dumping data for table class_managment.month: ~12 rows (approximately)
/*!40000 ALTER TABLE `month` DISABLE KEYS */;
INSERT INTO `month` (`id`, `month`) VALUES
	(1, 'January'),
	(2, 'February'),
	(3, 'March'),
	(4, 'April'),
	(5, 'May'),
	(6, 'June'),
	(7, 'July'),
	(8, 'August'),
	(9, 'September'),
	(10, 'October'),
	(11, 'November'),
	(12, 'December');
/*!40000 ALTER TABLE `month` ENABLE KEYS */;

-- Dumping structure for table class_managment.position
CREATE TABLE IF NOT EXISTS `position` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `position` varchar(35) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Dumping data for table class_managment.position: ~3 rows (approximately)
/*!40000 ALTER TABLE `position` DISABLE KEYS */;
INSERT INTO `position` (`id`, `position`) VALUES
	(1, 'Operator'),
	(2, 'Admin'),
	(3, 'Super Admin');
/*!40000 ALTER TABLE `position` ENABLE KEYS */;

-- Dumping structure for table class_managment.school
CREATE TABLE IF NOT EXISTS `school` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `school` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Dumping data for table class_managment.school: ~3 rows (approximately)
/*!40000 ALTER TABLE `school` DISABLE KEYS */;
INSERT INTO `school` (`id`, `school`) VALUES
	(1, 'SMC'),
	(2, 'MSC'),
	(3, 'Isipathana Collage');
/*!40000 ALTER TABLE `school` ENABLE KEYS */;

-- Dumping structure for table class_managment.seminar
CREATE TABLE IF NOT EXISTS `seminar` (
  `id` varchar(25) NOT NULL,
  `subject_id` int(11) NOT NULL,
  `teacher_id` varchar(25) NOT NULL,
  `desc` text NOT NULL,
  `date` date DEFAULT NULL,
  `start_time` time DEFAULT NULL,
  `end_time` time DEFAULT NULL,
  `fee` double DEFAULT NULL,
  `status` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_seminar_subject1` (`subject_id`),
  KEY `fk_seminar_teacher1` (`teacher_id`),
  CONSTRAINT `fk_seminar_subject1` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_seminar_teacher1` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table class_managment.seminar: ~2 rows (approximately)
/*!40000 ALTER TABLE `seminar` DISABLE KEYS */;
INSERT INTO `seminar` (`id`, `subject_id`, `teacher_id`, `desc`, `date`, `start_time`, `end_time`, `fee`, `status`) VALUES
	('SE-1', 1, 'TE-1', 'asasa', '2020-05-02', '08:00:00', '05:00:00', 300, 1),
	('SE-2', 1, 'TE-1', 'asasa', '2020-05-02', '10:00:00', '01:00:00', 500, 1);
/*!40000 ALTER TABLE `seminar` ENABLE KEYS */;

-- Dumping structure for table class_managment.seminar_payment
CREATE TABLE IF NOT EXISTS `seminar_payment` (
  `id` int(11) NOT NULL,
  `date` datetime DEFAULT NULL,
  `teacher_id` varchar(25) NOT NULL,
  `amount` double DEFAULT NULL,
  `staff_id` varchar(25) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_seminar_payment_teacher1` (`teacher_id`),
  KEY `fk_seminar_payment_staff1` (`staff_id`),
  CONSTRAINT `fk_seminar_payment_staff1` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_seminar_payment_teacher1` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table class_managment.seminar_payment: ~0 rows (approximately)
/*!40000 ALTER TABLE `seminar_payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `seminar_payment` ENABLE KEYS */;

-- Dumping structure for table class_managment.seminar_payment_seminars
CREATE TABLE IF NOT EXISTS `seminar_payment_seminars` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `total` double(11,2) NOT NULL DEFAULT '0.00',
  `seminar_payment_id` int(11) NOT NULL DEFAULT '0',
  `seminar_id` varchar(25) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_seminar_payment_seminars_seminar_payment` (`seminar_payment_id`),
  KEY `FK_seminar_payment_seminars_seminar` (`seminar_id`),
  CONSTRAINT `FK_seminar_payment_seminars_seminar` FOREIGN KEY (`seminar_id`) REFERENCES `seminar` (`id`),
  CONSTRAINT `FK_seminar_payment_seminars_seminar_payment` FOREIGN KEY (`seminar_payment_id`) REFERENCES `seminar_payment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table class_managment.seminar_payment_seminars: ~0 rows (approximately)
/*!40000 ALTER TABLE `seminar_payment_seminars` DISABLE KEYS */;
/*!40000 ALTER TABLE `seminar_payment_seminars` ENABLE KEYS */;

-- Dumping structure for table class_managment.special_class
CREATE TABLE IF NOT EXISTS `special_class` (
  `id` varchar(25) NOT NULL,
  `desc` text NOT NULL,
  `date` date DEFAULT NULL,
  `start_time` time DEFAULT NULL,
  `end_time` time DEFAULT NULL,
  `class_id` varchar(25) NOT NULL,
  `status` int(2) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `fk_special_class_class1` (`class_id`),
  CONSTRAINT `fk_special_class_class1` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table class_managment.special_class: ~3 rows (approximately)
/*!40000 ALTER TABLE `special_class` DISABLE KEYS */;
INSERT INTO `special_class` (`id`, `desc`, `date`, `start_time`, `end_time`, `class_id`, `status`) VALUES
	('SC-1', 'before teram test traning\n', '2020-05-01', '08:00:00', '12:00:00', 'CL-2', 1),
	('SC-2', 'sas', '2020-05-01', '13:21:38', '13:35:39', 'CL-5', 1),
	('SC-3', 'dsdsds', '2020-04-14', '01:53:00', '01:53:00', 'CL-1', 1);
/*!40000 ALTER TABLE `special_class` ENABLE KEYS */;

-- Dumping structure for table class_managment.special_class_attendance
CREATE TABLE IF NOT EXISTS `special_class_attendance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `special_class_id` varchar(25) NOT NULL,
  `student_id` varchar(25) NOT NULL,
  `date` date DEFAULT NULL,
  `time` time DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_special_class_attendance_special_class1` (`special_class_id`),
  KEY `fk_special_class_attendance_student1` (`student_id`),
  CONSTRAINT `fk_special_class_attendance_special_class1` FOREIGN KEY (`special_class_id`) REFERENCES `special_class` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_special_class_attendance_student1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

-- Dumping data for table class_managment.special_class_attendance: ~1 rows (approximately)
/*!40000 ALTER TABLE `special_class_attendance` DISABLE KEYS */;
INSERT INTO `special_class_attendance` (`id`, `special_class_id`, `student_id`, `date`, `time`) VALUES
	(9, 'SC-1', 'st-2', '2020-05-01', '17:32:55');
/*!40000 ALTER TABLE `special_class_attendance` ENABLE KEYS */;

-- Dumping structure for table class_managment.special_class_leaving
CREATE TABLE IF NOT EXISTS `special_class_leaving` (
  `id` int(11) unsigned zerofill NOT NULL,
  `date` date DEFAULT NULL,
  `time` time DEFAULT NULL,
  `special_class_id` varchar(25) NOT NULL,
  `student_id` varchar(25) NOT NULL,
  `reason` text,
  `staff_id` varchar(25) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_special_class_leaving_special_class1` (`special_class_id`),
  KEY `fk_special_class_leaving_student1` (`student_id`),
  KEY `fk_special_class_leaving_staff1` (`staff_id`),
  CONSTRAINT `fk_special_class_leaving_special_class1` FOREIGN KEY (`special_class_id`) REFERENCES `special_class` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_special_class_leaving_staff1` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_special_class_leaving_student1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table class_managment.special_class_leaving: ~0 rows (approximately)
/*!40000 ALTER TABLE `special_class_leaving` DISABLE KEYS */;
/*!40000 ALTER TABLE `special_class_leaving` ENABLE KEYS */;

-- Dumping structure for table class_managment.staff
CREATE TABLE IF NOT EXISTS `staff` (
  `id` varchar(25) NOT NULL,
  `fname` varchar(25) DEFAULT NULL,
  `lname` varchar(25) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `nic` varchar(15) DEFAULT NULL,
  `contact` varchar(12) DEFAULT NULL,
  `address` text,
  `date_reg` datetime DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `username` varchar(15) DEFAULT NULL,
  `password` varchar(15) DEFAULT NULL,
  `path` varchar(45) DEFAULT NULL,
  `status` int(2) DEFAULT NULL,
  `theme` int(2) DEFAULT '1',
  `city_id` int(11) NOT NULL,
  `position_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_staff_city` (`city_id`),
  KEY `fk_staff_position1` (`position_id`),
  CONSTRAINT `fk_staff_city` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_staff_position1` FOREIGN KEY (`position_id`) REFERENCES `position` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table class_managment.staff: ~5 rows (approximately)
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` (`id`, `fname`, `lname`, `dob`, `nic`, `contact`, `address`, `date_reg`, `email`, `username`, `password`, `path`, `status`, `theme`, `city_id`, `position_id`) VALUES
	('EM-1', 'Dananjaya', 'Peiris', '2020-04-30', '12', '11111', '11111', '2020-04-18 18:11:41', 'abhishekpeiris9@gmail.com', 'danu', '123123', 'src/Resources/images/staff/1587213701039.png', 1, 3, 2, 2),
	('EM-2', 'abhishek', 'dananjayaaa', '2001-05-07', 'ewew', '07867573476', 'Bolawalana', '2020-04-18 20:22:24', 'AbhishekDananjaya1@yahoo.com', 'abhi', '123', 'src/Resources/images/staff/1587221544508.png', 1, 1, 1, 3),
	('EM-3', '11', '111', '2020-02-18', 'qwqwq', '11', '111', '2020-04-18 21:01:55', 'dananjayapeiris54@gmail.com', '111', '123456', 'src/Resources/images/staff/1587223914887.png', 1, 1, 4, 2),
	('EM-4', 'test', 'test', '2020-04-28', 'test', 'test', 'test		\n', '2020-04-28 14:18:13', 'jenetpeiris63@gmail.com', 'test', '123', 'src/Resources/images/staff/1588063692895.png', 1, 5, 1, 1),
	('EM-5', 'test', 'test', '2020-04-29', 'sdsd', 'sdsd', 'sdsd', '2020-04-29 06:40:39', 'sdsd', 'ds', '78e47466', 'src/Resources/images/staff/1588122639338.png', 0, 1, 1, 1);
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;

-- Dumping structure for table class_managment.student
CREATE TABLE IF NOT EXISTS `student` (
  `id` varchar(25) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `nic` varchar(15) DEFAULT NULL,
  `contact1` varchar(12) DEFAULT NULL,
  `contact2` varchar(12) DEFAULT NULL,
  `address` text,
  `date_reg` datetime DEFAULT NULL,
  `stream` varchar(25) DEFAULT NULL,
  `gender` varchar(5) DEFAULT NULL,
  `status` int(2) DEFAULT NULL,
  `path` varchar(50) DEFAULT NULL,
  `sms` int(2) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `grade_id` int(11) DEFAULT NULL,
  `school_id` int(11) NOT NULL,
  `city_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_student_city1` (`city_id`),
  KEY `fk_student_school1` (`school_id`),
  KEY `FK_student_grade` (`grade_id`),
  CONSTRAINT `fk_student_city1` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_student_grade` FOREIGN KEY (`grade_id`) REFERENCES `grade` (`id`),
  CONSTRAINT `fk_student_school1` FOREIGN KEY (`school_id`) REFERENCES `school` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table class_managment.student: ~14 rows (approximately)
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` (`id`, `name`, `dob`, `nic`, `contact1`, `contact2`, `address`, `date_reg`, `stream`, `gender`, `status`, `path`, `sms`, `email`, `grade_id`, `school_id`, `city_id`) VALUES
	('ST-1', 'wewe', '2020-02-12', '11', '1212', '2323', '232', '2020-04-20 20:22:27', '2323', 's', 1, 'src/Resources/images/student/1587394347838.pn2', 2, 'abhishekpeiris9@gmail.com', 6, 2, 5),
	('ST-10', 'thikshan', '2020-04-25', 'asas', 'asa', 'sasas', 'asa', '2020-04-25 11:06:38', 'asaas', 'M', 1, 'src/Resources/images/student/1587792998600.png', 1, 'asa', 8, 1, 1),
	('ST-11', 'panda', '0199-10-21', 'asasa', 'asas', 'aasasas', 'as', '2020-04-25 12:20:56', 'sas', 'M', 1, 'src/Resources/images/student/1587797456703.png', 1, 'as', 3, 1, 1),
	('ST-12', 'asasa', '2020-04-25', 'asas', 'asasa', 'asas', 'asa', '2020-04-25 12:25:27', 'asas', 'M', 1, 'src/Resources/images/student/1587797727242.png', 1, 'as', 1, 1, 1),
	('ST-13', 'wewew', '2020-04-25', 'ewew', 'ewewew', 'wewewew', 'ewewe', '2020-04-25 23:29:27', 'we', 'M', 1, 'src/Resources/images/student/1587837567717.png', 1, 'wew', 1, 1, 1),
	('ST-14', 'asas', '2020-04-28', 'sas', 'aasa', 'sasa', 'as', '2020-04-28 22:19:18', 'asa', 'M', 1, 'src/Resources/images/student/1588092558821.png', 1, 'sa', 1, 1, 1),
	('ST-2', 'abhi', '2001-05-04', '1', 'qwq', '0786745678', 'bolawalana', '2020-04-20 20:26:35', 'tec', 'M', 1, 'src/Resources/images/student/1587530614006.png', 1, 'AbhishekDananjaya1@yahoo.com', 5, 2, 5),
	('ST-3', '3232', '2020-04-20', '32', 'qwqwqw', '', 'sds', '2020-04-20 20:27:14', 'sds', 'M', 1, 'src/Resources/images/student/1587394634295.png', 1, 'dananjayapeiris54@gmail.com', 3, 1, 1),
	('ST-4', 'xzx', '2020-04-20', '123', 'xzx', 'zxz', 'zx', '2020-04-20 20:51:27', 'zxzx', 'M', 1, 'src/Resources/images/student/1587396087436.png', 1, 'jenetpeiris63@gmail.com', 7, 1, 1),
	('ST-5', 'asas', '2020-04-20', '1234', 'ass', 'asaa', 'asa', '2020-04-20 20:52:12', 'asa', 'F', 1, 'src/Resources/images/student/1587526676405.png', 1, 'abhishekpeiris9@gmail.com', 7, 1, 1),
	('ST-6', '1111111', '2020-04-20', 'asa', 'asas', 'asa', 'asa', '2020-04-20 22:18:22', 'asa', 'M', 1, 'src/Resources/images/student/1587401302001.png', 0, 'zxxzxz', 8, 1, 1),
	('ST-7', '222222', '2020-04-20', 'dsds', 'sd', 'sdsd', 'dfdfd', '2020-04-20 22:21:15', 'sds', 'M', 1, 'src/Resources/images/student/1587401475735.png', 0, '12212', 3, 1, 1),
	('ST-8', 'Dananjaya Peiris', '2001-06-18', '200112345643', '0773423345', '', 'seeduwa ', '2020-04-22 10:42:34', 'Maths', 'M', 1, 'src/Resources/images/student/1587532354593.png', 1, 'dananjaya@gmail.com', 7, 2, 8),
	('ST-9', 'dsdsdsd', '2020-04-23', 'sdss', 'dsdsd', 'sdsds', 'sdsd', '2020-04-23 09:52:52', 'dsd', 'M', 1, 'src/Resources/images/student/1587615772769.png', 1, 'sdsd', 6, 1, 1);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;

-- Dumping structure for table class_managment.student_class
CREATE TABLE IF NOT EXISTS `student_class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `note` text NOT NULL,
  `status` int(2) NOT NULL DEFAULT '0',
  `student_id` varchar(25) NOT NULL,
  `class_id` varchar(25) NOT NULL,
  `student_class_card_type_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_student_class_student1` (`student_id`),
  KEY `fk_student_class_class1` (`class_id`),
  KEY `FK_student_class_student_class_card_type` (`student_class_card_type_id`),
  CONSTRAINT `fk_student_class_class1` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_student_class_student1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_student_class_student_class_card_type` FOREIGN KEY (`student_class_card_type_id`) REFERENCES `student_class_card_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=latin1;

-- Dumping data for table class_managment.student_class: ~21 rows (approximately)
/*!40000 ALTER TABLE `student_class` DISABLE KEYS */;
INSERT INTO `student_class` (`id`, `note`, `status`, `student_id`, `class_id`, `student_class_card_type_id`) VALUES
	(12, ' ', 1, 'ST-2', 'CL-3', 1),
	(13, 'sdsdsd', 1, 'ST-2', 'CL-2', 2),
	(14, 'weew', 1, 'ST-3', 'CL-2', 2),
	(15, ' ', 0, 'ST-4', 'CL-2', 1),
	(16, 'asasas', 1, 'ST-5', 'CL-1', 2),
	(17, ' ', 1, 'ST-6', 'CL-2', 1),
	(18, ' ', 1, 'ST-7', 'CL-2', 1),
	(19, ' ', 1, 'ST-3', 'CL-1', 1),
	(20, 'xccx', 1, 'ST-2', 'CL-1', 3),
	(21, 'sds', 1, 'ST-2', 'CL-1', 2),
	(22, ' ', 1, 'ST-8', 'CL-3', 1),
	(23, 'sddsdsd', 1, 'ST-8', 'CL-2', 2),
	(24, 'sdsdsdsds', 1, 'ST-8', 'CL-1', 3),
	(25, ' ', 1, 'ST-6', 'CL-1', 1),
	(26, 'ssdsds', 1, 'ST-9', 'CL-2', 2),
	(28, ' ', 1, 'ST-10', 'CL-1', 1),
	(29, 'halffffffff\n', 1, 'ST-11', 'CL-2', 2),
	(30, 'freee', 1, 'ST-12', 'CL-2', 3),
	(31, 'free  test\n', 1, 'ST-13', 'CL-2', 3),
	(32, ' ', 1, 'ST-14', 'CL-3', 1),
	(33, 'sdsds', 1, 'ST-3', 'CL-3', 2);
/*!40000 ALTER TABLE `student_class` ENABLE KEYS */;

-- Dumping structure for table class_managment.student_class_card_type
CREATE TABLE IF NOT EXISTS `student_class_card_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Dumping data for table class_managment.student_class_card_type: ~3 rows (approximately)
/*!40000 ALTER TABLE `student_class_card_type` DISABLE KEYS */;
INSERT INTO `student_class_card_type` (`id`, `type`) VALUES
	(1, 'Full'),
	(2, 'Half'),
	(3, 'Free');
/*!40000 ALTER TABLE `student_class_card_type` ENABLE KEYS */;

-- Dumping structure for table class_managment.student_class_fees
CREATE TABLE IF NOT EXISTS `student_class_fees` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `total` double(11,2) DEFAULT NULL,
  `year` year(4) DEFAULT NULL,
  `month_id` int(11) DEFAULT NULL,
  `student_class_id` int(11) DEFAULT NULL,
  `fees_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_student_class_fees_month` (`month_id`),
  KEY `FK_student_class_fees_student_class` (`student_class_id`),
  KEY `FK_student_class_fees_fees` (`fees_id`),
  CONSTRAINT `FK_student_class_fees_fees` FOREIGN KEY (`fees_id`) REFERENCES `fees` (`id`),
  CONSTRAINT `FK_student_class_fees_month` FOREIGN KEY (`month_id`) REFERENCES `month` (`id`),
  CONSTRAINT `FK_student_class_fees_student_class` FOREIGN KEY (`student_class_id`) REFERENCES `student_class` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=latin1;

-- Dumping data for table class_managment.student_class_fees: ~33 rows (approximately)
/*!40000 ALTER TABLE `student_class_fees` DISABLE KEYS */;
INSERT INTO `student_class_fees` (`id`, `total`, `year`, `month_id`, `student_class_id`, `fees_id`) VALUES
	(15, 2800.00, '2020', 4, 13, 9),
	(16, 2800.00, '2020', 4, 14, 10),
	(17, 2800.00, '2020', 4, 15, 11),
	(18, 2500.00, '2020', 4, 16, 12),
	(19, 2800.00, '2020', 4, 17, 13),
	(20, 2800.00, '2020', 4, 18, 14),
	(21, 1800.00, '2020', 4, 19, 17),
	(23, 2400.00, '2020', 5, 21, 18),
	(24, 1800.00, '2020', 6, 22, 19),
	(25, 2400.00, '2020', 6, 23, 19),
	(26, 1000.00, '2020', 6, 24, 19),
	(28, 2400.00, '2020', 4, 26, 21),
	(30, 2500.00, '2020', 4, 28, 23),
	(31, 2400.00, '2020', 10, 29, 24),
	(32, 2000.00, '2020', 4, 30, 25),
	(33, 2000.00, '2020', 4, 31, 26),
	(35, 1000.00, '2020', 3, 12, 28),
	(36, 1500.00, '2020', 3, 19, 29),
	(37, 400.00, '2020', 3, 14, 29),
	(38, 1800.00, '2020', 4, 32, 30),
	(39, 400.00, '2020', 5, 23, 31),
	(40, 800.00, '2020', 3, 15, 32),
	(41, 800.00, '2020', 2, 15, 33),
	(42, 800.00, '2020', 1, 15, 34),
	(43, 1500.00, '2020', 2, 19, 46),
	(44, 1300.00, '2020', 4, 33, 51),
	(45, 1000.00, '2020', 5, 22, 52),
	(46, 400.00, '2020', 4, 23, 52),
	(47, 0.00, '2020', 5, 24, 52),
	(50, 1000.00, '2020', 2, 12, 59),
	(51, 500.00, '2020', 3, 33, 60),
	(52, 400.00, '2020', 2, 14, 61),
	(53, 1000.00, '2020', 4, 12, 64);
/*!40000 ALTER TABLE `student_class_fees` ENABLE KEYS */;

-- Dumping structure for table class_managment.subject
CREATE TABLE IF NOT EXISTS `subject` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subject` varchar(45) DEFAULT NULL,
  `grade_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_subject_grade1` (`grade_id`),
  CONSTRAINT `fk_subject_grade1` FOREIGN KEY (`grade_id`) REFERENCES `grade` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

-- Dumping data for table class_managment.subject: ~20 rows (approximately)
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` (`id`, `subject`, `grade_id`) VALUES
	(1, 'Sinhala', 1),
	(2, 'Maths', 2),
	(3, 'Science', 2),
	(4, 'English', 3),
	(5, 'Maths', 1),
	(6, 'Econ', 7),
	(7, 'Accounts', 7),
	(8, 'ICT', 7),
	(9, 'Science', 1),
	(10, 'Econ', 8),
	(11, 'Accounts', 8),
	(12, 'Sinhala', 8),
	(13, 'Political', 8),
	(14, 'Chemistry', 8),
	(15, 'Physics', 8),
	(16, 'ICT', 8),
	(17, 'Science for Technology', 8),
	(18, 'Maths', 3),
	(19, 'Maths', 6),
	(20, 'Sinhala', 5);
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;

-- Dumping structure for table class_managment.teacher
CREATE TABLE IF NOT EXISTS `teacher` (
  `id` varchar(25) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `nic` varchar(15) DEFAULT NULL,
  `contact1` varchar(12) DEFAULT NULL,
  `contact2` varchar(12) DEFAULT NULL,
  `address` text,
  `email` varchar(50) DEFAULT NULL,
  `date_reg` datetime DEFAULT NULL,
  `path` varchar(50) DEFAULT NULL,
  `status` int(2) DEFAULT NULL,
  `city_id` int(11) NOT NULL,
  `balance` double(11,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`id`),
  KEY `fk_teacher_city1` (`city_id`),
  CONSTRAINT `fk_teacher_city1` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table class_managment.teacher: ~9 rows (approximately)
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` (`id`, `name`, `dob`, `nic`, `contact1`, `contact2`, `address`, `email`, `date_reg`, `path`, `status`, `city_id`, `balance`) VALUES
	('TE-1', 'abhishek', '2021-05-04', '121212112', '0783467123', '0768945348', 'negombo', 'abhishekpeiris9@gmail.com', '2022-04-17 14:08:24', 'src/Resources/images/teacher/1587124825358.png', 1, 1, -207.00),
	('TE-2', 'peiris', '2020-04-17', '222', '0717787890', '0773423456', '2222', 'AbhishekDananjaya1@yahoo.com', '2020-04-17 14:58:19', 'src/Resources/images/teacher/1587115699422.png', 1, 1, 615.00),
	('TE-3', 'ASSAsdsd', '2020-04-05', '2121', 'zxzx sdsd', 'ASSAsdsd', ' zxsdsd', 'jenetpeiris63@gmail.com', '2020-04-17 16:01:30', 'src/Resources/images/teacher/1587119490741.png', 1, 1, 0.00),
	('TE-4', 'ssasdaasd', '2020-04-17', 'adaddsadsa', '111', 'ssasdaasd', 'asadasa', 'dananjayapeiris54@gmail.com', '2020-04-17 16:06:01', 'src/Resources/images/teacher/1587119761106.png', 1, 1, 0.00),
	('TE-5', 'sdsss111', '2020-04-19', 'weewsdss', 'wewesds', 'sdsss111', 'wewsdssdssd', 'wewewdsds', '2020-04-17 16:09:40', 'src/Resources/images/teacher/1587119980304.png', 0, 1, 0.00),
	('TE-6', '222', '2020-04-17', '111asasaasas', 'aasa111', '111asasa', '111asasasaasa', 'sasa11', '2020-04-17 17:16:44', 'src/Resources/images/teacher/1587124004295.png', 0, 1, 0.00),
	('TE-7', 'ewewe', '2020-04-17', 'wewe', 'wewew', 'wwwww', 'wewewe', 'wewe', '2020-04-17 17:39:23', 'src/Resources/images/teacher/1587125363418.png', 0, 2, 0.00),
	('TE-8', 'sdasas', '2020-04-17', 'sasas', 'sasa', 'asas', 'sasasa', 'abhishekpeiris9@gmai.com', '2020-04-17 23:09:02', 'src/Resources/images/teacher/1587145142920.png', 1, 4, 0.00),
	('TE-9', 'zxzxz', '2020-04-17', '2121a', 'zxzx', 'xzxz', 'zxzx', 'abhishekdananjaya1@yahoo.coma', '2020-04-17 23:11:31', 'src/Resources/images/teacher/1587145291553.png', 0, 1, 0.00);
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;

-- Dumping structure for table class_managment.timetable
CREATE TABLE IF NOT EXISTS `timetable` (
  `is` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(10) NOT NULL DEFAULT '0',
  `staff_id` varchar(25) NOT NULL DEFAULT '0',
  `class_id` varchar(25) NOT NULL DEFAULT '0',
  PRIMARY KEY (`is`),
  KEY `FK_timetable_staff` (`staff_id`),
  KEY `FK_timetable_class` (`class_id`),
  CONSTRAINT `FK_timetable_class` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`),
  CONSTRAINT `FK_timetable_staff` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table class_managment.timetable: ~0 rows (approximately)
/*!40000 ALTER TABLE `timetable` DISABLE KEYS */;
/*!40000 ALTER TABLE `timetable` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
