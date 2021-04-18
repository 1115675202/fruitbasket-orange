INSERT INTO `orange`.`permission`(`id`, `deleted`, `gmt_create`, `gmt_modified`, `breadcrumbs`, `description`, `level`, `permission_name`, `permission_url`, `pid`, `sort_value`) VALUES (1, b'0', '2021-04-18 16:35:28', '2021-04-18 16:35:30', '/0', '系统', 1, '系统', '/', 0, 0);

INSERT INTO `orange`.`role`(`id`, `deleted`, `gmt_create`, `gmt_modified`, `description`, `pid`, `role_code`, `role_level`, `role_name`, `role_path`, `sort_value`) VALUES (1, b'0', '2021-04-18 16:37:43', '2021-04-18 16:37:45', NULL, 0, 'SYS', 1, '管理员', '/', 0);

INSERT INTO `orange`.`user`(`id`, `deleted`, `gmt_create`, `gmt_modified`, `avatar_link`, `birthday`, `id_card_no`, `real_name`, `sex`) VALUES (1, b'0', '2021-04-18 16:48:52', '2021-04-18 16:48:54', '/', '2021-04-18', '0', 'admin', 1);

INSERT INTO `orange`.`user_account`(`id`, `deleted`, `gmt_create`, `gmt_modified`, `credential`, `identifier`, `identity_type`, `user_id`) VALUES (1, b'1', '2021-04-18 16:40:10', '2021-04-18 16:40:12', 'admin', 'admin', 1, 1);

INSERT INTO `orange`.`role_permissions`(`permission_id`, `role_id`) VALUES (1, 1);
INSERT INTO `orange`.`role_users`(`roles_id`, `users_id`) VALUES (1, 1);