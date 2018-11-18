insert into superhero (id, firstname, lastname, superheroname) values (1, 'Bat', 'Man', 'batman');

insert into superhero (id, firstname, lastname, superheroname) values (2, 'Spider', 'Man', 'spiderman');

insert into superhero (id, firstname, lastname, superheroname) values (3, 'Super', 'Man', 'superman');

insert into superhero (id, firstname, lastname, superheroname) values (4, 'Aqua', 'Man', 'aquaman');

insert into mission (id, name, is_deleted, is_completed) values (1, 'Rescue world', false, false);

insert into mission (id, name, is_deleted, is_completed) values (2, 'Save the bees', false, false);

insert into mission (id, name, is_deleted, is_completed) values (3, 'Kill Bill', false, false);

insert into mission (id, name, is_deleted, is_completed) values (4, 'Adopt a cat', false, false);

insert into mission (id, name, is_deleted, is_completed) values (5, 'Make a friend', false, false);

insert into mission (id, name, is_deleted, is_completed) values (6, 'Deleted mission', true, false);

insert into mission (id, name, is_deleted, is_completed) values (7, 'Unable to remove a completed mission', false, true);

insert into superhero_mission (superhero_id, mission_id) values (1, 1);

insert into superhero_mission (superhero_id, mission_id) values (1, 3);

insert into superhero_mission (superhero_id, mission_id) values (1, 6);

insert into superhero_mission (superhero_id, mission_id) values (1, 7);

insert into superhero_mission (superhero_id, mission_id) values (2, 1);

insert into superhero_mission (superhero_id, mission_id) values (2, 2);