SET search_path TO app;

DROP FUNCTION IF EXISTS get_checks_lucky_days(integer) cascade;
DROP FUNCTION IF EXISTS fnc_trg_check_single_root_task() cascade;
DROP FUNCTION IF EXISTS fnc_inconsistent_checks(varchar, integer) cascade;
DROP FUNCTION IF EXISTS fnc_trg_finish_after_start() cascade;
DROP FUNCTION IF EXISTS fnc_trg_verter_after_p2p() cascade;
DROP FUNCTION IF EXISTS fnc_trg_mutual_friendship() cascade;
DROP PROCEDURE IF EXISTS import_from_csv(varchar, varchar, varchar) cascade;
DROP PROCEDURE IF EXISTS export_to_csv(varchar, varchar, varchar) cascade;
DROP FUNCTION IF EXISTS fnc_trg_transfer_p2p_point() cascade;
DROP FUNCTION IF EXISTS fnc_trg_xp_validation() cascade;
DROP FUNCTION IF EXISTS get_early_bird_peers(time, integer) cascade;
DROP FUNCTION IF EXISTS peers_lefts_last_days(integer, integer) cascade;
DROP PROCEDURE IF EXISTS drop_table_name_prefixed_tables() cascade;
DROP PROCEDURE IF EXISTS list_user_defined_scalar_functions(out integer) cascade;
DROP PROCEDURE IF EXISTS output_list_scalar_functions_result() cascade;
DROP PROCEDURE IF EXISTS drop_all_dml_triggers(out integer) cascade;
DROP PROCEDURE IF EXISTS output_drop_all_dml_triggers_result() cascade;
DROP PROCEDURE IF EXISTS output_funcs_and_procs_with_string(varchar) cascade;
DROP PROCEDURE IF EXISTS add_p2p_check(varchar, varchar, varchar, check_status, time) cascade;
DROP PROCEDURE IF EXISTS add_verter_check(varchar, varchar, check_status, time) cascade;
DROP FUNCTION IF EXISTS get_transferred_points_summary() cascade;
DROP FUNCTION IF EXISTS get_peer_xp_info() cascade;
DROP FUNCTION IF EXISTS get_whole_day_peers(date) cascade;
DROP FUNCTION IF EXISTS calculate_peer_points_change() cascade;
DROP FUNCTION IF EXISTS fast_calculate_peer_points_change() cascade;
DROP FUNCTION IF EXISTS get_popular_tasks_for_check() cascade;
DROP FUNCTION IF EXISTS get_peers_completed_whole_block(varchar) cascade;
DROP FUNCTION IF EXISTS get_recommended_checkers() cascade;
DROP FUNCTION IF EXISTS calculate_block_participation(varchar, varchar) cascade;
DROP FUNCTION IF EXISTS calculate_birthday_check_percent() cascade;
DROP FUNCTION IF EXISTS find_peers_not_completed_task(varchar, varchar, varchar) cascade;
DROP FUNCTION IF EXISTS get_task_hierarchy() cascade;
DROP FUNCTION IF EXISTS get_checks_lucky_days(integer) cascade;
DROP FUNCTION IF EXISTS get_peer_with_maximum_xp() cascade;
DROP FUNCTION IF EXISTS early_entries_by_month_of_birth() cascade;

-- Удаление таблиц
DROP TABLE IF EXISTS peers CASCADE;
DROP TABLE IF EXISTS tasks CASCADE;
DROP TABLE IF EXISTS p2p CASCADE;
DROP TABLE IF EXISTS verter CASCADE;
DROP TABLE IF EXISTS checks CASCADE;
DROP TABLE IF EXISTS transferred_points CASCADE;
DROP TABLE IF EXISTS friends CASCADE;
DROP TABLE IF EXISTS recommendations CASCADE;
DROP TABLE IF EXISTS xp CASCADE;
DROP TABLE IF EXISTS time_tracking CASCADE;

-- Удаление типа
DROP TYPE IF EXISTS check_status CASCADE;

