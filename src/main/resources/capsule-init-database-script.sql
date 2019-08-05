--
-- Table structure for table `parent_task`
--

CREATE TABLE `parent_task` (
  `parent_id` int(10) NOT NULL,
  `parent_task` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Parent Task Table';

--
-- Initial Values for table `parent_task`
--

INSERT INTO `parent_task` (`parent_id`, `parent_task`) VALUES
(101, 'Requirement Gathering '),
(102, 'Analysis'),
(103, 'Design'),
(104, 'Coding'),
(105, 'Unit Testing'),
(106, 'System Integration Testing '),
(107, 'User Acceptance Testing'),
(108, 'Production Deployment'),
(109, 'Post Production Support');

-- --------------------------------------------------------

--
-- Table structure for table `task`
--

CREATE TABLE `task` (
  `task_id` int(11) NOT NULL,
  `parent_id` int(11) NOT NULL,
  `task` varchar(500) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `priority` int(11) NOT NULL,
  `in_progress` boolean NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Task Table';


--
-- Indexes for table `parent_task`
--
ALTER TABLE `parent_task`
  ADD PRIMARY KEY (`parent_id`);

--
-- Indexes for table `task`
--
ALTER TABLE `task`
  ADD PRIMARY KEY (`task_id`),
  ADD KEY `task_fk` (`parent_id`);

--
-- AUTO_INCREMENT for table `task`
--
ALTER TABLE `task`
  MODIFY `task_id` int(11) NOT NULL AUTO_INCREMENT;


--
-- Constraints for table `task`
--
ALTER TABLE `task`
  ADD CONSTRAINT `task_fk` FOREIGN KEY (`parent_id`) REFERENCES `parent_task` (`parent_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;
