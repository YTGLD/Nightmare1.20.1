package com.ytgld.seeking_immortals;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import org.apache.commons.lang3.tuple.Pair;

public class Config {
    public static Config SERVER;
    public static ForgeConfigSpec fc;

    static {
        final Pair<Config, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Config::new);
        SERVER = specPair.getLeft();
        fc = specPair.getRight();
    }


    public final ForgeConfigSpec.BooleanValue nightmare_base_black_eye ;
    public final ForgeConfigSpec.DoubleValue nightmare_base_stone ;
    public final ForgeConfigSpec.DoubleValue nightmare_base_fool ;
    public final ForgeConfigSpec.IntValue nightmare_base_insight ;
    public final ForgeConfigSpec.IntValue nightmare_base_redemption ;
    public final ForgeConfigSpec.IntValue nightmare_base_reversal ;
    public final ForgeConfigSpec.IntValue nightmare_base_start ;
    public final ForgeConfigSpec.IntValue give_nightmare_base_insight_drug ;
    public Config(ForgeConfigSpec.Builder BUILDER){

        {
            BUILDER.push("噩梦");
            {
                BUILDER.push("获取");
                give_nightmare_base_insight_drug = BUILDER
                        .comment("疯狂灵药获取时要求的药水数量")
                        .defineInRange("give_nightmare_base_insight_drug", 9,1,100);
                BUILDER.pop();

            }
            {
                BUILDER.push("邪念之窥眸");
                nightmare_base_black_eye = BUILDER
                        .comment("近视效果开关")
                        .define("nightmare_base_black_eye", true);
                BUILDER.pop();

                BUILDER.push("死兆方尖碑");
                nightmare_base_stone = BUILDER
                        .comment("满血的受伤伤害")
                        .defineInRange("nightmare_base_stone", 5f,1,999);
                BUILDER.pop();

                BUILDER.push("愚者之危");
                nightmare_base_fool = BUILDER
                        .comment("最大处罚值，0.5就是50%")
                        .defineInRange("nightmare_base_fool", 0.5f,0,1);
                BUILDER.pop();

                BUILDER.push("噩梦洞悉者");
                nightmare_base_insight = BUILDER
                        .comment("附魔的减少值")
                        .defineInRange("nightmare_base_insight", 2,0,1000);
                BUILDER.pop();

                BUILDER.push("“救赎”");
                nightmare_base_redemption = BUILDER
                        .comment("属性衰败比例")
                        .defineInRange("nightmare_base_redemption", 15,0,100);
                BUILDER.pop();

                BUILDER.push("颠倒之物");
                nightmare_base_reversal = BUILDER
                        .comment("每次死亡降低的最低值")
                        .defineInRange("nightmare_base_reversal", 4,0,100);
                BUILDER.pop();

                BUILDER.push("噩梦之起始");
                nightmare_base_start = BUILDER
                        .comment("护甲值的处罚")
                        .defineInRange("nightmare_base_start", 100,0,100);
                BUILDER.pop();
            }
            nightmareBaseMaxItem = BUILDER
                    .comment("“”噩梦基座“给玩家的罪孽数量")
                    .defineInRange("nig_", 5, 0, 7);

            Nightecora = BUILDER
                    .comment("Nightecora病毒的额外生命值惩罚，单位百分比")
                    .defineInRange("Nightecora_", 10, 0, 100);
            nightmare_base_redemption_deception = BUILDER
                    .comment("“欺骗”恢复的生命值，单位百分比")
                    .defineInRange("nightmare_base_redemption_deception", 100, 0, 100);
            nightmare_base_redemption_deception_time = BUILDER
                    .comment("“欺骗”恢复的生命值，单位秒")
                    .defineInRange("nightmare_base_redemption_deception_time", 7, 0, 100);

            nightmare_base_fool_bone = BUILDER
                    .comment("危险的头骨造成的额外伤害，“2”是两倍")
                    .defineInRange("nightmare_base_fool_bone", 1.25f, 0, 9999);

            nightmare_base_insight_drug = BUILDER
                    .comment("疯狂灵药的最大属性加成，单位百分比")
                    .defineInRange("nightmare_base_insight_drug", 80, 0, 99999);

            nightmare_base_insight_drug_2 = BUILDER
                    .comment("疯狂灵药的单物品计算的属性衰败，单位百分比")
                    .defineInRange("nightmare_base_insight_drug_2", 8, 0, 99999);

            nightmare_base_insight_insane = BUILDER
                    .comment("癫狂之石的杀死生物后获得的伤害加成，单位百分比")
                    .defineInRange("nightmare_base_insight_insane", 30, 0, 99999);
            BUILDER.pop();

        }

        {
            BUILDER.push("测试");
            x = BUILDER.comment("x")
                    .defineInRange("x", 0, -360f, 360f);
            y = BUILDER.comment("y")
                    .defineInRange("y", 0, -360f, 360f);
            z = BUILDER.comment("z")
                    .defineInRange("z", 0, -360f, 360f);


            BUILDER.pop();
        }



        BUILDER.build();
    }


    public   ForgeConfigSpec.IntValue Nightecora ;


    public   ForgeConfigSpec.IntValue nightmare_base_redemption_deception ;

    public   ForgeConfigSpec.DoubleValue nightmare_base_fool_bone ;
    public   ForgeConfigSpec.IntValue nightmare_base_insight_drug ;
    public   ForgeConfigSpec.IntValue nightmare_base_insight_drug_2 ;

    public   ForgeConfigSpec.IntValue nightmare_base_insight_insane ;
    public   ForgeConfigSpec.IntValue nightmare_base_redemption_deception_time ;


    public   ForgeConfigSpec.IntValue nightmareBaseMaxItem ;


    public   ForgeConfigSpec.DoubleValue x ;
    public   ForgeConfigSpec.DoubleValue y ;
    public   ForgeConfigSpec.DoubleValue z ;



    @SubscribeEvent
    public static void onLoad(final ModConfigEvent.Loading configEvent) {
    }

    @SubscribeEvent
    public static void onFileChange(final ModConfigEvent.Reloading configEvent) {
    }

}
