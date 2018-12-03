CREATE SEQUENCE public.arena_arenaid_seq NO MINVALUE NO MAXVALUE NO CYCLE;
ALTER TABLE public.arena ALTER COLUMN arenaid SET DEFAULT nextval('public.arena_arenaid_seq');
ALTER SEQUENCE public.arena_arenaid_seq OWNED BY public.arena.arenaid;

CREATE SEQUENCE public.district_districtid_seq NO MINVALUE NO MAXVALUE NO CYCLE;
ALTER TABLE public.district ALTER COLUMN districtid SET DEFAULT nextval('public.district_districtid_seq');
ALTER SEQUENCE public.district_districtid_seq OWNED BY public.district.districtid;

CREATE SEQUENCE public.game_gameid_seq NO MINVALUE NO MAXVALUE NO CYCLE;
ALTER TABLE public.game ALTER COLUMN gameid SET DEFAULT nextval('public.game_gameid_seq');
ALTER SEQUENCE public.game_gameid_seq OWNED BY public.game.gameid;

CREATE SEQUENCE public.hook_hookid_seq NO MINVALUE NO MAXVALUE NO CYCLE;
ALTER TABLE public.hook ALTER COLUMN hookid SET DEFAULT nextval('public.hook_hookid_seq');
ALTER SEQUENCE public.hook_hookid_seq OWNED BY public.hook.hookid;

CREATE SEQUENCE public.location_locationid_seq NO MINVALUE NO MAXVALUE NO CYCLE;
ALTER TABLE public.location ALTER COLUMN locationid SET DEFAULT nextval('public.location_locationid_seq');
ALTER SEQUENCE public.location_locationid_seq OWNED BY public.location.locationid;

CREATE SEQUENCE public.presentstotribute_sendingid_seq NO MINVALUE NO MAXVALUE NO CYCLE;
ALTER TABLE public.presentstotribute ALTER COLUMN sendingid SET DEFAULT nextval('public.presentstotribute_sendingid_seq');
ALTER SEQUENCE public.presentstotribute_sendingid_seq OWNED BY public.presentstotribute.sendingid;

CREATE SEQUENCE public.price_priceid_seq NO MINVALUE NO MAXVALUE NO CYCLE;
ALTER TABLE public.price ALTER COLUMN priceid SET DEFAULT nextval('public.price_priceid_seq');
ALTER SEQUENCE public.price_priceid_seq OWNED BY public.price.priceid;

CREATE SEQUENCE public.productsandlocation_applyingid_seq NO MINVALUE NO MAXVALUE NO CYCLE;
ALTER TABLE public.productsandlocation ALTER COLUMN applyingid SET DEFAULT nextval('public.productsandlocation_applyingid_seq');
ALTER SEQUENCE public.productsandlocation_applyingid_seq OWNED BY public.productsandlocation.applyingid;

CREATE SEQUENCE public.shop_productid_seq NO MINVALUE NO MAXVALUE NO CYCLE;
ALTER TABLE public.shop ALTER COLUMN productid SET DEFAULT nextval('public.shop_productid_seq');
ALTER SEQUENCE public.shop_productid_seq OWNED BY public.shop.productid;

CREATE SEQUENCE public.skill_skillid_seq NO MINVALUE NO MAXVALUE NO CYCLE;
ALTER TABLE public.skill ALTER COLUMN skillid SET DEFAULT nextval('public.skill_skillid_seq');
ALTER SEQUENCE public.skill_skillid_seq OWNED BY public.skill.skillid;

CREATE SEQUENCE public.status_statusid_seq NO MINVALUE NO MAXVALUE NO CYCLE;
ALTER TABLE public.status ALTER COLUMN statusid SET DEFAULT nextval('public.status_statusid_seq');
ALTER SEQUENCE public.status_statusid_seq OWNED BY public.status.statusid;

CREATE SEQUENCE public.training_trainingid_seq NO MINVALUE NO MAXVALUE NO CYCLE;
ALTER TABLE public.training ALTER COLUMN trainingid SET DEFAULT nextval('public.training_trainingid_seq');
ALTER SEQUENCE public.training_trainingid_seq OWNED BY public.training.trainingid;

CREATE SEQUENCE public.tribute_tributeid_seq NO MINVALUE NO MAXVALUE NO CYCLE;
ALTER TABLE public.tribute ALTER COLUMN tributeid SET DEFAULT nextval('public.tribute_tributeid_seq');
ALTER SEQUENCE public.tribute_tributeid_seq OWNED BY public.tribute.tributeid;

CREATE SEQUENCE public.userlogin_loginid_seq NO MINVALUE NO MAXVALUE NO CYCLE;
ALTER TABLE public.userlogin ALTER COLUMN loginid SET DEFAULT nextval('public.userlogin_loginid_seq');
ALTER SEQUENCE public.userlogin_loginid_seq OWNED BY public.userlogin.loginid;

CREATE SEQUENCE public.users_userid_seq NO MINVALUE NO MAXVALUE NO CYCLE;
ALTER TABLE public.users ALTER COLUMN userid SET DEFAULT nextval('public.users_userid_seq');
ALTER SEQUENCE public.users_userid_seq OWNED BY public.users.userid;

CREATE SEQUENCE public.userskill_userskillid_seq NO MINVALUE NO MAXVALUE NO CYCLE;
ALTER TABLE public.userskill ALTER COLUMN userskillid SET DEFAULT nextval('public.userskill_userskillid_seq');
ALTER SEQUENCE public.userskill_userskillid_seq OWNED BY public.userskill.userskillid;

CREATE SEQUENCE public.weapon_weaponid_seq NO MINVALUE NO MAXVALUE NO CYCLE;
ALTER TABLE public.weapon ALTER COLUMN weaponid SET DEFAULT nextval('public.weapon_weaponid_seq');
ALTER SEQUENCE public.weapon_weaponid_seq OWNED BY public.weapon.weaponid;

CREATE SEQUENCE public.weaponsingame_weaponingameid_seq NO MINVALUE NO MAXVALUE NO CYCLE;
ALTER TABLE public.weaponsingame ALTER COLUMN weaponingameid SET DEFAULT nextval('public.weaponsingame_weaponingameid_seq');
ALTER SEQUENCE public.weaponsingame_weaponingameid_seq OWNED BY public.weaponsingame.weaponingameid;