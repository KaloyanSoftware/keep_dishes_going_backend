-- ============================================
-- Script: Clean up and update restaurant.menu_events
-- ============================================

BEGIN;

-- 1. Delete all menu events with event_type DISH_OUT_OF_STOCK or DISH_BACK_IN_STOCK
DELETE FROM restaurant.menu_events
WHERE event_type IN ('DISH_OUT_OF_STOCK', 'DISH_BACK_IN_STOCK');

-- 2. Set dish_published = TRUE where event_type is DISH_PUBLISHED
UPDATE restaurant.menu_events
SET dish_published = TRUE
WHERE event_type = 'DISH_PUBLISHED';

-- 3. Set dish_published = FALSE where event_type is DISH_UNPUBLISHED
UPDATE restaurant.menu_events
SET dish_published = FALSE
WHERE event_type = 'DISH_UNPUBLISHED';

COMMIT;
