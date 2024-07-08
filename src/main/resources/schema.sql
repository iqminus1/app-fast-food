create unique index if not exists user_email_ui on users(email) where deleted = false;
create unique index if not exists user_phone_number_ui on users(phone_number) where deleted = false;

create unique index if not exists category_name_ru_ui on category(name_ru,parent_category_id) where deleted = false;
create unique index if not exists category_name_uz_ui on category(name_uz,parent_category_id) where deleted = false;
create unique index if not exists category_ru_ui on category(name_ru) where deleted = false and parent_category_id is null;
create unique index if not exists category_uz_ui on category(name_uz) where deleted = false and parent_category_id is null;

create unique index if not exists product_ru_ui on product(name_ru,category_id) where deleted = false;
create unique index if not exists product_uz_ui on product(name_uz,category_id) where deleted = false;

create unique index if not exists region_name_ru_ui on region(name_ru) where deleted = false;
create unique index if not exists region_name_uz_ui on region(name_uz) where deleted = false;

create unique index if not exists filial_location_ui on filial(latitude,longitude) where deleted = false;

create unique index if not exists prize_product_ui on prize_product(product_id) where deleted = false;

create unique index if not exists user_basket_ui on user_basket(user_id,product_id) where deleted = false;