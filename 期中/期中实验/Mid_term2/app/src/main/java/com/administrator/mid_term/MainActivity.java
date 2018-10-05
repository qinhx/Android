package com.administrator.mid_term;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;

import com.administrator.mid_term.ArcMenu.OnMenuItemClickListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private List<Person> list=new ArrayList<>();
    private ArcMenu mArcMenuLeftTop;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mArcMenuLeftTop = (ArcMenu) findViewById(R.id.id_arcmenu1);
        //动态添加一个MenuItem
        ImageView people = new ImageView(this);
        people.setImageResource(R.mipmap.composer_with);
        people.setTag("People");
        mArcMenuLeftTop.addView(people);
        mArcMenuLeftTop.bringToFront();
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.W_Rv);
        recyclerView.addItemDecoration(new SpaceItemDecoration(30));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final String[] name=new String[]{
                "關羽 字云长 ？-219",
                "趙雲 字子龙 ？-229",
                "諸葛亮 字孔明 181-234(54岁)",
                "劉備 字玄德 161-223(63岁)",
                "姜維 字伯约 202-264(63岁)",
                "宗瑋 ？-？",
                "周瑜 字公瑾 175-210(36岁)",
                "孫策 字伯符 175-200(26岁)",
                "諸葛瑾 字子瑜 174-241(68岁)",
                "李崇 ？-？",
                "敬懷皇后 ？-？",
                "張約 ？-？",
                "曹操 字孟德 155-220(66岁)",
                "于禁 字文则 ？-221",
                "郭嘉 字奉孝 170-207(38岁)",
                "司馬懿 字仲达 179-251(73岁)",
                "鄧艾 字士载 197-264(68岁)",
                "曹爽 字昭伯 ？-249"
        };
        final String[] palce=new String[]{
                "籍贯：司隶河东郡解",
                "籍贯：冀州常山国真定",
                "籍贯：徐州琅邪国阳都",
                "籍贯：幽州涿郡涿",
                "籍贯：凉州汉阳郡冀",
                "籍贯：暂无相关记载",
                "籍贯：扬州庐江郡舒(安徽合肥市庐江县西南)",
                "籍贯：扬州吴郡富春",
                "籍贯：徐州琅邪国阳都",
                "籍贯：暂无相关记载",
                "籍贯：荆州南阳郡(河南南阳市)",
                "籍贯：暂无相关记载",
                "籍贯：豫州沛国谯(安徽亳州市亳县)",
                "籍贯：兖州泰山郡巨平",
                "籍贯：豫州颍川郡阳翟",
                "籍贯：司隶河内郡温",
                "籍贯：荆州南阳郡棘阳",
                "籍贯：豫州沛国谯"

        };
         final String[]master=new String[]{
                "主效蜀势力 曾效力过：东汉 蜀 魏",
                "主效蜀势力 曾效力过：蜀 袁绍",
                "主效蜀势力 曾效力过：蜀 ",
                "主效蜀势力 曾效力过：东汉 刘表 蜀 魏 袁绍 ",
                "主效蜀势力 曾效力过：蜀 ",
                "主效蜀势力 曾效力过：蜀 ",
                "主效吴势力 曾效力过：吴 袁术",
                "主效吴势力 曾效力过：吴 袁术 ",
                "主效吴势力 曾效力过：吴 袁术",
                "主效吴势力 曾效力过：吴 袁术 ",
                "主效吴势力 曾效力过：吴 袁术",
                "主效吴势力 曾效力过：吴 袁术 ",
                "主效魏势力 曾效力过：魏 ",
                "主效魏势力 曾效力过：魏 ",
                "主效魏势力 曾效力过：魏 ",
                "主效魏势力 曾效力过：魏 ",
                "主效魏势力 曾效力过：魏 ",
                "主效魏势力 曾效力过：魏 "

        };
        final String[] story=new String[]{
                "前将军。本字长生，亡命奔涿郡。与张飞追随刘备征战，当刘备为平原相时，他们俩为别部司马。二人与刘备寝则同床，恩若兄弟。稠人广坐则侍立终日。随同曹操和刘备讨吕布于下邳，事后为朝廷封为中郎将。当刘备袭杀曹操的徐州刺史车冑后，以关羽镇下邳太守。曹操东征破刘备，关羽被俘，被拜为偏将军，对他礼遇很优厚。白马之战时关羽万军中刺敌主帅颜良，被封为汉寿亭侯，报了曹操之恩后便告辞，寻找刘备。长阪之战刘备败北，抄近路赴汉津与关羽的数百只船汇合至江夏。赤壁之战孙刘联军胜利后，关羽在其后的江陵之战中绝北道，阻隔曹操的援军，为周瑜能攻下江陵创造有利的条件。事后遙领襄阳太守、拜为荡寇将军。诸葛亮等人入蜀增援刘备，关羽便镇荊州。刘备自立为汉中王，被拜为前将军，假节钺。关羽乘汉水暴涨之机出兵襄樊，更在于禁七军为水所淹乘船进攻，梁、郏、陆浑各县的盗贼有些远远地领受了关羽的官印封号，威震华夏；曹操一度想迁都避其锋芒。后因孙权背盟投曹，后方为吕蒙所破，关羽便退兵，但终为孙权军所擒杀。",
                "以英勇善战、一身是胆著称。初为本郡所举，将义从吏兵诣公孙瓒。时袁绍称冀州牧，瓒深忧州人之从绍也，善云来附，遂与瓒征讨。时先主亦依讬瓒，每接纳云，云得深自结讬。瓒遣先主为田楷拒袁绍，云遂随从，为先主主骑。云以兄丧，辞瓒暂归，先主知其不反，捉手而别，云辞曰：“终不背德也。”先主就袁绍，云见于邺。先主与云同床眠卧，密遣云合募得数百人，皆称刘左将军部曲，绍不能知。遂随先主至荆州。夏侯惇战于博望，生获夏侯兰。兰是云乡里人，少小相知，云白先主活之，荐兰明于法律，以为军正。及先主为曹公所追于当阳长阪，弃妻子南走，云身抱弱主，保护甘夫人，皆得免难。迁为牙门将军。先主入蜀，云留荆州。从平江南，以为偏将军，领桂阳太守，代赵范。范寡嫂曰樊氏，有国色，范欲以配云。云固辞不许。先主入益州，云领留营司马。时先主孙夫人欲将后主还吴，云与张飞勒兵截江，乃得后主还。先主自葭萌还攻刘璋，召诸葛亮。亮率云与张飞等俱溯江西上，平定郡县。至江州，分遣云从外水上江阳，与亮会于成都。成都既定，以云为翊军将军。孙权袭荆州，先主大怒，欲讨权。云谏止，不从。乃留云督江州。先主失利于秭归，云进兵至永安，吴军已退。建兴元年，为中护军、征南将军，封永昌亭侯，迁镇东将军。五年，随诸葛亮驻汉中。明年，亮出军，扬声由斜谷道，曹真遣大众当之。亮令云与邓芝往拒，而身攻祁山。云、芝兵弱敌强，失利于箕谷，然敛众固守，不至大败。军退，贬为镇军将军。七年卒，追谥顺平侯。",
                "政治家、军事家，被誉为“千古良相”的典范。父母早亡，由叔父玄抚养长大，后因徐州之乱，避乱荆州，潜心向学，淡泊明志。后受刘备三顾之礼，提出著名的《隆中对》，策动孙、刘联盟，于赤壁之战中大破曹操，奠定三国鼎立的基础。蜀汉建立，拜为丞相。刘备伐吴失败，受托孤于永安，辅佐幼主，外联东吴，内修政理，南征平叛，北抗强魏。为完成统一中原，兴复汉室的大业，先后五次进攻魏国，在治国、治军等方面发挥了非凡的才能，是以民用其力，百姓不忿；又推演兵法，作“八阵图”，造损益连弩、木牛流马，与名将司马懿、张郃等交锋，屡操胜算，最后一次北伐时采取分兵屯田之策，与司马懿大军相持百余日，但不幸因积劳成疾而逝世，享年五十四岁，谥曰忠武侯。其“鞠躬尽力，死而后已”的高尚品格，千百年来一直为人们所敬仰和怀念。",
                "蜀汉的开国皇帝，相传是汉景帝之子中山靖王刘胜的后代。刘备少年丧父，与母亲贩鞋织草席为生。黄巾起义时，刘备组织义兵，随政府军剿除黄巾，有功，任安喜县尉，不久因鞭打督邮弃官。后诸侯割据，刘备势力弱小，经常寄人篱下，先后投靠过公孙瓒、曹操、袁绍、刘表等人，几经波折，却仍无自己的地盘。赤壁之战之际，刘备联吴抗曹，取得胜利，从东吴处“借”到荆州，迅速发展起来，吞并益州，占领汉中，建立蜀汉政权。后关羽战死，荆州被孙权夺取，刘备于称帝后伐吴，在夷陵之战中被陆逊击败，病逝于白帝城，临终托孤于诸葛亮。",
                "幼年丧父和母亲生活，喜爱郑玄的经学，曾为魏中郎将，参天水郡军事。诸葛亮第一次北伐后投蜀汉，因他忠勤时事、思虑精密、敏于军事、即有胆义，又兼心存汉室，故深得诸葛亮的器重。诸葛亮于五丈原病逝后，姜维令杨仪反旗鸣鼓，导致尾追的司马懿退兵。姜维继诸葛亮之略，伺图中原，恢复汉室。又因其熟悉西方风俗，欲以羌胡为羽翼断陇西为蜀汉所有。蒋琬和费祎在位时实行保境安民，姜维每次出兵不过万人，但费祎被刺杀后能实行自己的志向，于是伺机累次兵伐中原，降李简、斩徐质、大破王经，一时挫魏国之威。但也有麹城被夺、段谷及侯和之败。后请刘禅杀专权的宦人黄皓不果，以屯田之名避禍沓中。司马昭大举伐蜀姜维上表请朝廷增援，但黄皓并不理会。姜维为邓艾军所缠，后用计令诸葛绪误以他将袭雍州而得脫，于剑阁拒守钟会十余万大军。奈先有江由守将马邈投降，再有诸葛瞻不听黄崇抢占涪，更战死于绵竹，蜀汉震恐后从投降派谯周之议，后主投降，并敕令姜维也降，将士得知后奋怒斩石。姜维乃佯降于钟会，看出他阴有异志策应他造反，图谋杀会后重扶汉室，乃事败，姜维及妻子皆伏诛。",
                "太中大夫。刘备章武二年（222）冬，孙权闻备住白帝，甚懼，遣使请和。备许之，遣玮报命。",
                "自幼与孙策交好，孙策于袁术麾下初崛起时曾随之扫荡江东。后来回去镇守丹阳。袁术心慕周瑜的才干，欲聘周瑜为将，但是周瑜以袁术难成大事而拒绝。其后设法投奔孙策，为中郎将，孙策相待甚厚，又同时迎娶有「国色」之称的二乔，成为连襟。孙策遇刺身亡后，周瑜与张昭一起共同辅佐孙权，为中护军，执掌军政大事。赤壁大战期间，力主拒曹，而指挥全军在乌林迎击曹军取得胜利。赤壁大战之后，周瑜谏议孙权将刘备安抚在吴郡，以美女和玩物消磨其意志，但孙权未采纳。孙权后来采纳周瑜的谏议，拟出兵攻取蜀地，消灭张鲁，然后消灭曹操，周瑜在江陵进行军事准备时死于巴陵，时年三十六岁。孙权曾为其素服吊丧。周瑜性情开朗，气度宽宏，深得维恩显着。精通乐律，即使在醉酒时也能听出音律的错误。",
                "汉讨逆将军、会稽太守。长沙太守坚长子，坚初兴义兵，策将母徙居舒，与周瑜相友，收合士大夫，江、淮间人咸向之。坚薨，还葬曲阿。已乃渡江居江都。徐州牧陶谦深忌策。策舅吴景，时为丹杨太守，策乃载母徙曲阿，与吕范、孙河俱就景，因缘召募得数百人。兴平元年，从袁术。术甚奇之，以坚部曲还策。太傅马日磾杖节安集关东，在寿春以礼辟策，表拜怀义校尉。术初许策为九江太守，已而更用丹杨陈纪。后术欲攻徐州，从庐江太守陆康求米三万斛。康不与，术大怒。策昔曾诣康，康不见，使主簿接之。策尝衔恨。术遣策攻康，拔之，术复用其故吏刘勋为太守，策益失望。先是，刘繇为扬州刺史，州旧治寿春。寿春，术已据之，繇乃渡江治曲阿，与术相攻。策乃说术，乞助景等平定江东。术表策为折冲校尉，行殄寇将军，兵财千余，骑数十匹，宾客原从者数百人。策母先自曲阿徙于历阳，策又徙母阜陵，渡江转斗，所向皆破，莫敢当其锋，而军令整肃，百姓怀之。又破严白虎等。时袁术僭号，策以书责而绝之。曹公表策为讨逆将军，封为吴侯。是时哀绍方强，而策并江东，曹公力未能逞，且欲抚之。建安五年，曹公与袁绍相拒于官渡，策阴欲袭许，迎汉帝，密治兵，部署诸将。未发，会为故吴郡太守许贡客所杀。先是，策杀贡，贡小子与客亡匿江边。策单骑出，卒与客遇，客击伤策。创甚，请张昭等嘱以后事，呼权佩以印绶，至夜卒，时年二十六。权称尊号，追谥策曰长沙桓王。",
                "诸葛亮之兄，经鲁肃推荐，为东吴效力。胸怀宽广，温厚诚信，得到孫权的深深信赖，努力缓和蜀汉与东吴的关系。吕蒙去世后，诸葛谨代吕蒙领南郡太守，驻守公安。孙权称帝后，诸葛谨官至大将军，领豫州牧。军国大政，多所咨谋，为主所重。赤乌四年（241）卒，年六十八，遗令薄葬。",
                "任中书郎。太元中，曾奉命迎罗阳县“神”王表至建业。太平三年（258），孙綝废孙亮为会稽王，遣崇夺孙亮玺绶。",
                "孙权妻。孙休母。王氏被选入宫，到嘉禾年间得到孙权宠幸，并且诞下六子孙休。赤乌五年(242年)，孙权立孙和为太子，因孙和之母王夫人尊贵，宫中所有曾被孙权宠幸的女子，都要离宫居住，于是王氏被迁居至公安。后来逝世并下葬。会稽王太平三年(258年)，孙綝废黜孙亮，拥立琅邪王孙休继位，并派遣使者追尊王氏。",
                "孙亮时，为散骑常侍。建兴二年（253），武卫将军孙峻谋杀诸葛恪，约有所觉察，与朱恩密琉告恪，恪未果断离开殿堂。峻刀斫恪之时，约从旁斫峻，伤左手，峻应手斫约，断右臂",
                "政治家、军事家、诗人，统一了北方、挟天子以令诸侯，戎马一生。曹操父亲曹嵩为宦官曹腾养子，曹腾为汉相曹参之后。曹操谥武王，曹丕称帝后，追尊他为武皇帝，史称魏武帝。曹操在北方屯田，兴修水利，解决了军粮缺乏的问题，对农业生产的恢复有一定作用；用人唯才，罗致地主阶级中下层人物，抑制豪强，加强集权。颂令收田租亩四升，戶出绢二匹、锦二斤，为日后的租庸调之始。所统治的地区社会经济得到恢复和发展。草创九品官人法。精兵法，著《孙子略解》、《兵书接要》等书。善诗歌，《蒿里行》、《观沧海》等篇，抒发自己的政治抱负，并反映汉末人民的苦难生活，气魄雄伟，慷慨悲凉。散文亦清峻整洁。著作有《魏武帝集》。葬于高陵。",
                "最早随鲍信起兵，鲍信死后归附曹操，被任为官军司马。从此跟随曹操四处征战。曹操兵败宛城，曹军的青州兵四处抢劫，被于禁追杀后就去告发于禁叛变，恰好此时张绣追兵来攻，于禁就先扎下营寨才去见曹操，曹操问他怎么不先来解释，于禁认为分辩事小，退敌事大，曹操因此十分高兴，于是封他为益寿亭侯。后来败吕布，破袁绍，于禁与其它五良将都轮流任先锋官而表现活跃。还有一次曹操厌恶朱灵并想撤掉他的兵权，就派于禁去办，于禁手执文书仅带数十骑就出色地完成了任务，他就是这样地令人畏惧，被迁至左将军，假节钺。关羽包围曹仁于樊城，于禁去救援，但遇上汉水暴涨为关羽乘船进攻，于禁投降了敌人，而他手下的庞德却不屈被杀。后来关羽被孙权所破，于禁也就归属了吴国，直到黄初二年才被送回魏国，那时他都已经须发皆白，面容憔悴，泪流满面。曹丕就命他去拜见高陵（曹操的陵墓），因为陵中画有他屈膝降敌，庞德不屈的图画，所以于禁不久就羞愧发病而死，死后追谥为厉侯。",
                "曹操之司空军祭酒。郭嘉少年时就有卓识远见，隐居乡间只与众多英豪为伍。早期曾经投靠过袁绍，但看到袁绍无果断的抉择力和任用察识贤能的才能，成不了大事便离开袁绍。后来由于曹操的谋士戏志才的死，郭嘉受曹操谋士荀彧的举荐，和曹操详谈后互相肯定对方的能力。袁绍曾经送信羞辱曹操，郭嘉提出十胜十败论，对比曹操和绍的优胜劣败，鼓舞了曹操；又分析时局，认为先扫平吕布，再谋取天下。曹操东征吕布，久攻下邳不下，便有意退兵，但郭嘉与荀攸都认为吕布强攻下必败，决沂、泗水灌城，终破城。郭嘉认为不该让因吕布逆袭下投靠的刘备邀击袁术，认为这是放虎归山，后来刘备果然杀曹操置的徐州刺史车冑而叛。曹操想趁刘备立足未稳时速破，但诸将忧虑袁绍出兵，郭嘉指出袁绍反应迟缓应不会有大规模用兵，支持曹操的决定，事情结果也如他所料。官渡对峙时，孙策有袭击许昌之意，但是郭嘉分析认定孙策必为小人所害，稳定了曹操的信心。袁绍死后，建议曹操暂时退兵，让袁绍二子相残，后袁谭来投，曹军再次进击攻下袁尚的邺城。在南皮杀掉复叛的袁谭后，劝曹操多用河北贤才。追袭袁熙、袁尚余党时劝曹操兵贵神速，直指受袁氏之恩的乌丸大本营柳城，终破三郡乌丸並让他们臣服。可惜因水土不服，气候恶劣，日夜急行操劳过度而病死。曹操深感痛惜，泪流满面，对手下众人说：“大家跟我差不多年岁，只有郭嘉最年轻，本来想托嘱后事，但是却中年而丧，令人叹息。”赤壁战败后更慨叹：“若奉孝在，不使孤至此！",
                "世家豪族出身，早年在郡中作小吏，后被曹操辟为文学掾。曹操称魏王后，司马懿为太子中庶子，佐助曹丕，与陈群等同列“四友”，是曹丕智囊团的主要人物。襄樊之战时和蒋济劝曹操勿迁都，可割南封孙权，令其袭杀关羽，事后如他所料。曹丕代汉后，司马懿节节高升，开始掌握重权。曹叡继位后，司马懿坐镇宛城，督荆、豫二州军事，主持荆州对吴攻战，后接替曹真总管对蜀汉的防御任务，数次成功防御诸葛亮北伐。在淮南和上邽的屯田成效显著，为魏军屯的创办人。后出兵平公孙渊。曹芳继位后，司马懿、曹爽共同辅政。司马懿受曹爽排挤，后发动高平陵之变诛杀曹爽一族，自此掌握魏国大权。司马懿病逝后，魏国政权仍由其儿子把持，多年后导致晋朝代魏的发生。司马懿被后代追封为晋朝皇帝。",
                "年少时就已是孤儿，曹操破荆州被徙至汝南，后随母至颍川襄城，读陈寔碑文后改名范，字士则。因宗族有相同者，故改为艾。因口吃被改为稻田守丛草吏。每当他看到高山大泽，总是规划指点哪里可以设置军营。为典农功曹时向中央汇报时因见司马懿，得到赏识被辟为太尉府掾，迁尚书郎。后著《济河论》陈述淮南屯田的方案，实施后军粮大增。又与郭淮拒姜维于洮阳有功，赐爵关内侯，加讨寇将军。司马师新辅政，多纳其计谋，乃分南匈奴为两部削弱其势。改任汝南太守时政绩卓著。后迁兗州刺史，于讨伐毋丘俭之役有功，进封方城乡侯。从陈泰解王经之围于狄道有功，升为安西将军，假节、领护东羌校尉。拒姜维于长城后迁征西将军。又破姜维于侯和，姜维退保沓中。司马昭大举伐蜀，邓艾险渡阴平，降江由，败诸葛瞻后，刘禅投降。以邓艾为太尉，增邑二万户，封子二人亭侯，各食邑干户。但其后邓艾的所作所为让司马昭疑虑，加上钟会等上表邓艾有叛乱的跡象，被槛车收押。钟会之乱时，邓艾和儿子忠为田续所杀。后得段灼上表为其平反。",
                "曹真之子，为魏明帝曹睿所宠信，不断升迁至武卫将军。曹睿病危时，拜曹爽为大将军，与太尉司马懿一同受遗诏辅少主。魏帝曹芳继位后，曹爽兄弟、党羽均在朝中担任要职，宠贵无比，与司马懿一派争权。司马懿假意退避，却暗中做出准备。一次曹爽兄弟随皇帝外出祭祀先帝，司马懿趁机发动政变，史称“高平陵事变”。曹爽优柔寡断，最终投降，被司马懿夷三族，党羽皆被剿灭。"


        };
        final String[]pic=new String[]{
                "guanyu",
                "zhaoyun",
                "zhugeliang",
                "liubei",
                "jiangwei",
                "zongwei",
                "zhouyu",
                "sunce",
                "zhugejin",
                "lichong",
                "jinhuaihuanghou",
                "liangguang",
                "caocao",
                "yujin",
                "guojia",
                "simayi",
                "dengai",
                "caoshaung"
        };
        final int contry[]=new int[]{1,1,1,1,1,2,2,2,2,2,2,2,3,3,3,3,3,3};
        for ( int i=0;i<name.length;i++){
            int im=getImageId(pic[i]);
            Person person=new Person(name[i],palce[i],master[i],story[i],im,contry[i]);
            list.add(person);
        }

        final    CommonAdapter<Person> commonAdapter=new CommonAdapter<Person>(this,R.layout.w_rec,list) {
            @Override
            public void convert(ViewHolder holder, Person person) {
                TextView textView=holder.getView(R.id.W_tv);
                textView.setText(person.getName());
                ImageView imageView=holder.getView(R.id.W_im);
                imageView.setImageResource(person.getPicId());
            }
        };
        recyclerView.setAdapter(commonAdapter);
        commonAdapter.setOnItemClickListener(new CommonAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent=new Intent(MainActivity.this,MainActivity_2.class);
                intent.putExtra("name",list.get(position).getName());
                intent.putExtra("place",list.get(position).getPlace());
                intent.putExtra("story",list.get(position).getStory());
                intent.putExtra("master",list.get(position).getMaster());
                intent.putExtra("picId",list.get(position).getPicId());
                intent.putExtra("contry",list.get(position).getContry());
                startActivity(intent);
            }

            @Override
            public void onLongClick(int position) {
            }
        });


        EditText search=(EditText)findViewById(R.id.search);
            search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (!v.getText().toString().isEmpty()){
                          list.clear();
                        for (int i=0;i<name.length;i++){
                        if (name[i].contains(v.getText().toString())){
                            int im=getImageId(pic[i]);
                            Person person=new Person(name[i],palce[i],master[i],story[i],im,contry[i]);
                            list.add(person);
                            commonAdapter.notifyDataSetChanged();
                        }
                     }
                    }
                    if (v.getText().toString().isEmpty()){
                        list.clear();
                        for ( int i=0;i<name.length;i++){
                            int im=getImageId(pic[i]);
                            Person person=new Person(name[i],palce[i],master[i],story[i],im,contry[i]);
                            list.add(person);
                            commonAdapter.notifyDataSetChanged();
                        }

                    }


                    return false;
                }
            });

        /**************************/
        mArcMenuLeftTop
                .setOnMenuItemClickListener(new OnMenuItemClickListener()
                {
                    @Override
                    public void onClick(View view, int pos)
                    {
                        if(pos==0){
                            Intent intent = new Intent(MainActivity.this,MainActivity.class);
                            startActivity(intent);
                        }
                        //到时候如果有更多的界面xml的话，就在这里进行调整即可，加入更多的else if
                        else if(pos==1){
                            Intent intent = new Intent(MainActivity.this,MainActivity_2.class);
                            startActivity(intent);;
                        }
                        else if(pos==2){
                            Intent intent = new Intent(MainActivity.this,MainActivity_3.class);
                            startActivity(intent);
                        }

                    }
                });
    }


    public  int getImageId(String name){
        Context context=getBaseContext();
        int id=getResources().getIdentifier(name,"mipmap",context.getPackageName());
        return  id;
    }
}
