-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jun 08, 2021 at 10:50 PM
-- Server version: 10.3.21-MariaDB
-- PHP Version: 7.2.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `computer_shop`
--

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
                              `id` int(11) NOT NULL,
                              `category_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` VALUES
(1, 'Desktop'),
(2, 'Laptop'),
(3, 'Server');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
                          `order_id` bigint(20) NOT NULL,
                          `order_status` varchar(50) NOT NULL,
                          `status_details` varchar(200) DEFAULT NULL,
                          `order_date_time` datetime DEFAULT NULL,
                          `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` VALUES
(1, 'GENERATED', NULL, '2020-10-09 15:08:35', 2),
(2, 'GENERATED', NULL, '2020-10-14 18:27:41', 4),
(3, 'GENERATED', NULL, '2020-10-20 10:50:27', 2),
(4, 'GENERATED', NULL, '2020-11-08 02:18:09', 2),
(5, 'GENERATED', NULL, '2020-11-08 09:30:15', 2),
(6, 'GENERATED', NULL, '2020-11-14 18:06:36', 2),
(7, 'GENERATED', NULL, '2020-11-14 21:46:13', 9),
(8, 'GENERATED', NULL, '2020-11-14 22:11:16', 2),
(9, 'GENERATED', NULL, '2020-11-14 22:13:55', 2),
(10, 'GENERATED', NULL, '2020-11-14 22:30:44', 2);

-- --------------------------------------------------------

--
-- Table structure for table `order_product`
--

CREATE TABLE `order_product` (
                                 `order_id` bigint(20) NOT NULL,
                                 `product_id` bigint(20) NOT NULL,
                                 `product_price` decimal(20,2) NOT NULL,
                                 `number_in_order` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `order_product`
--

INSERT INTO `order_product` VALUES
(1, 1, '29999.00', 1),
(1, 3, '38699.00', 1),
(2, 1, '29999.00', 1),
(2, 3, '38699.00', 1),
(3, 1, '29999.00', 1),
(3, 3, '38699.00', 1),
(4, 1, '29999.00', 1),
(4, 3, '38699.00', 1),
(5, 3, '38699.00', 1),
(6, 1, '29999.00', 1),
(6, 3, '38699.00', 1),
(6, 2, '39999.00', 1),
(7, 1, '29999.00', 1),
(7, 3, '38699.00', 2),
(8, 1, '29999.00', 1),
(8, 3, '38699.00', 1),
(9, 1, '29999.00', 1),
(9, 3, '38699.00', 1);

-- --------------------------------------------------------

--
-- Table structure for table `producers`
--

CREATE TABLE `producers` (
                             `id` smallint(6) NOT NULL,
                             `producer_name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `producers`
--

INSERT INTO `producers` VALUES
(1, 'Asus'),
(2, 'Apple'),
(3, 'Dell'),
(4, 'HP');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
                            `product_id` bigint(20) NOT NULL,
                            `category_id` int(11) NOT NULL,
                            `producer_id` smallint(6) NOT NULL,
                            `name` varchar(255) NOT NULL,
                            `short_description` text NOT NULL,
                            `price` decimal(20,2) NOT NULL,
                            `image` varchar(255) NOT NULL,
                            `meta_title` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `products`
--

INSERT INTO `products` VALUES
(1, 2, 1, 'Asus ZenBook S (UX391FA-AH010T)\r\n', '13,3\" / 1920x1080 / IPS / Intel Core i7-8565U, 1,8-4,6 ГГц / Intel UHD Graphics 620/ DDR3 / 16 ГБ / SSD - 512 GB/\r\n\r\n', '29999.00', 'img/products/ua_noutbuk_asus_zenbook_13_ux334fac_ux334fac-a3047t_noutbuk_asus_zenbook_13_ux334fac_ux334fac-a3047t_2500_1673.jpg', 'qq'),
(2, 2, 1, 'Asus ZenBook UX325JA Grey (UX325JA-AH040T)', '13,3\" / 1920x1080 / Intel Core i7-1065G7, 1,3-3,9 ГГц / Intel Iris Plus Graphics / ОС - Windows 10 Home / DDR4 / 16 ГБ / SSD - 512 ГБ / Wi-Fi 802.11ax, Bluetooth', '39999.00', 'img/products/ua_noutbuk_asus_zenbook_s_ux391fa-ah010t_noutbuk_asus_zenbook_s_ux391fa-ah010t.jpg', 'qq'),
(3, 2, 1, 'Asus ZenBook 13 UX333FLC (UX333FLC-A3153T)\r\n', '13,3\" / 1920x1080 / Intel Core i7-10510U, 1,8-4,9 ГГц / NVIDIA GeForce MX 250, 2 GB / DDR3 / 16 ГБ / SSD - 512 GB / Wi-Fi 802.11ax, Bluetooth / HDMI ', '38699.00', 'img/products/ua_noutbuk_asus_zenbook_ux325ja_grey_ux325ja-ah040t_noutbuk_asus_zenbook_ux325ja_grey_ux325ja-ah040t_1100_764.jpg', 'qq');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
                         `id` bigint(20) NOT NULL,
                         `login` varchar(200) NOT NULL,
                         `first_name` varchar(200) NOT NULL,
                         `last_name` varchar(200) NOT NULL,
                         `email` varchar(200) NOT NULL,
                         `password` varchar(200) NOT NULL,
                         `password_salt` varchar(200),
                         `user_role` varchar(20) NOT NULL DEFAULT 'user'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` VALUES
(1, 'admin', 'Admin', 'Admin', 'admin@admin.com', '123','no', 'admin'),
(2, 'test', 'test', 'test', 'test', '123','no', 'user');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
    ADD PRIMARY KEY (`order_id`);

--
-- Indexes for table `order_product`
--
ALTER TABLE `order_product`
    ADD KEY `order` (`order_id`),
  ADD KEY `product` (`product_id`);

--
-- Indexes for table `producers`
--
ALTER TABLE `producers`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
    ADD PRIMARY KEY (`product_id`),
  ADD UNIQUE KEY `id` (`product_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
    ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `users_login_uindex` (`login`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
    MODIFY `order_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `producers`
--
ALTER TABLE `producers`
    MODIFY `id` smallint(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
    MODIFY `product_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `order_product`
--
ALTER TABLE `order_product`
    ADD CONSTRAINT `order` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `product` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;