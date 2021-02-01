DROP TABLE IF EXISTS `subways`;
CREATE TABLE `subways`  (
  `id` int NULL DEFAULT NULL,
  `line_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `stations`;
CREATE TABLE `stations`  (
  `id` int NOT NULL,
  `station_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `stops`;
CREATE TABLE `stops`  (
  `id` int NOT NULL,
  `station_id` int NOT NULL,
  `subway_id` int NOT NULL,
  `is_start_stop` tinyint NULL DEFAULT NULL,
  `is_end_stop` tinyint NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SELECT * FROM stops s,
	(SELECT subway_id FROM stops WHERE is_start_stop = 1 AND station_id = ?) tmp
WHERE s.subway_id = tmp.subway_id;

SELECT st.subway_id
FROM stops st
WHERE st.station_id IN (
	SELECT s.station_id FROM stops s,
		(SELECT subway_id FROM stops WHERE is_start_stop = 1 AND station_id = ?) tmp
	WHERE s.subway_id = tmp.subway_id);
