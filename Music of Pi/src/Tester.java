import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;import java.util.Arrays;
import java.util.Scanner;

public class Tester {
	public static int[] c = {1, 3, 5, 6, 8, 10, 0};
	public static int[] g = {8, 10, 0, 1, 3, 5, 7};
	public static int[] d = {3, 5, 7, 8, 10, 0, 2};
	public static int[] a = {10, 0, 2, 3, 5, 7, 9};
	public static int[] e = {5, 7, 9, 10, 0, 2, 4};
	public static int[] b = {0, 2, 4, 5, 7, 9, 11};
	public static int[] fsharp = {7, 9, 11, 0, 2, 4, 0};
	public static int[] dflat = {2, 4, 6, 7, 9, 11, 1};
	public static int[] aflat = {9, 11, 1, 2, 4, 6, 8};
	public static int[] eflat = {4, 6, 8, 9, 11, 1, 3};
	public static int[] bflat = {11, 1, 3, 4, 6, 8, 10};
	public static int[] f = {6, 8, 10, 11, 1, 3, 5};
	
	public static boolean contains(int[] array, int number){
		boolean isTrue = false;
		for(int vals:array)
			if(vals==number)
				isTrue = true;
		return isTrue;
	}
	
	public static boolean allTrue(ArrayList<Boolean> array){
		for(boolean vals: array)
			if(vals == false)
				return false;
		return true;
						
	}
	
	public static boolean allFalse(boolean[] array){
		for(boolean vals: array)
			if(vals == true)
				return false;
		return true;
						
	}
	public static void main(String[] args){
//		ArrayList<Integer> scale = new ArrayList<Integer>();
//		Scanner scanner = new Scanner(System.in);
//		while(true){
//			int tempInt = 0;
//			String temp = scanner.next();
//			switch(temp){
//				case "A": tempInt = 10;
//				break;
//				case "A#": tempInt = 11;
//				break;
//				case "B": tempInt = 0;
//				break;
//				case "C": tempInt = 1;
//				break;
//				case "C#": tempInt = 2;
//				break;
//				case "D": tempInt = 3;
//				break;
//				case "D#": tempInt = 4;
//				break;
//				case "E": tempInt = 5;
//				break;
//				case "F": tempInt = 6;
//				break;
//				case "F#": tempInt = 7;
//				break;
//				case "G": tempInt = 8;
//				break;
//				case "G#": tempInt = 9;
//				break;
//				
//			}
//			
//			scale.add(tempInt);
//			System.out.println(scale);
//		}
		
		ArrayList<Integer> random = new ArrayList<Integer>();
		for(int i = 0; i<1000; i++)	
			random.add((int)(Math.random()*12));
		BigDecimal pi = new BigDecimal("3.141592653589793238462643383279502884197169399375105820974944592307816406286208998628034825342117067982148086513282306647093844609550582231725359408128481117450284102701938521105559644622948954930381964428810975665933446128475648233786783165271201909145648566923460348610454326648213393607260249141273724587006606315588174881520920962829254091715364367892590360011330530548820466521384146951941511609433057270365759591953092186117381932611793105118548074462379962749567351885752724891227938183011949129833673362440656643086021394946395224737190702179860943702770539217176293176752384674818467669405132000568127145263560827785771342757789609173637178721468440901224953430146549585371050792279689258923542019956112129021960864034418159813629774771309960518707211349999998372978049951059731732816096318595024459455346908302642522308253344685035261931188171010003137838752886587533208381420617177669147303598253490428755468731159562863882353787593751957781857780532171226806613001927876611195"
			
				+ "90921642019893809525720106548586327886593615338182796823030195203530185296899577362259941389124972177528347913151557485724245415069595082953311686172785588907509838175463746493931925506040092770167113900984882401285836160356370766010471018194295559619894676783744944825537977472684710404753464620804668425906949129331367702898915210475216205696602405803815019351125338243003558764024749647326391419927260426992279678235478163600934172164121992458631503028618297455570674983850549458858692699569092721079750930295532116534498720275596023648066549911988183479775356636980742654252786255181841757467289097777279380008164706001614524919217321721477235014144197356854816136115735255213347574184946843852332390739414333454776241686251898356948556209921922218427255025425688767179049460165346680498862723279178608578438382796797668145410095388"
				+ "3786360950680064225125205117392984896084128488626945604241965285022210661186306744278622039194945047123713786960956364371917287467764657573962413890865832645995813390478027590099465764078951269468398352595709825822620522489407726719478268482601476990902640136394437455305068203496252451749399651431429809190659250937221696461515709858387410597885959772975498930161753928468138268683868942774155991855925245953959431049972524680845987273644695848653836736222626099124608051243884390451244136549762780797715691435997700129616089441694868555848406353422072225828488648158456028506016842739452267467678895252138522549954666727823986456596116354886230577456498035593634568174324112515076069479451096596094025228879710893145669136867228748940560101503308617928680920"
				+ "8747609178249385890097149096759852613655497818931297848216829989487226588048575640142704775551323796414515237462343645428584447952658678210511413547357395231134271661021359695362314429524849371871101457654035902799344037420073105785390621983874478084784896833214457138687519435064302184531910484810053706146806749192781911979399520614196634287544406437451237181921799983910159195618146751426912397489409071864942319615679452080951465502252316038819301420937621378559566389377870830390697920773467221825625996615014215030680384477345492026054146659252014974428507325186660021324340881907104863317346496514539057962685610055081066587969981635747363840525714591028970641401109712062804390397595156771577004203378699360072305587631763594218731251471205329281918261861258673215791984148488291644706095752706957220917567116722910981690915280173506712748583222871835209353965725121083579151369882091444210067510334671103141267111369908658516398315019701651511685171437657618351556508849099898599823873455283316355076479185358932261854896321329330898570642046752590709154814165498594616371802709819943099244889575712828905923233260972997120844335732654893823911932597463667305836041428138830320382490375898524374417029132765618093773444030707469211201913020330380197621101100449293215160842444859637669838952286847831235526582131449576857262433441893039686426243410773226978028073189154411010446823252716201052652272111660396665573092547110557853763466820653109896526918620564769312570586356620185581007293606598764861179104533488503461136576867532494416680396265797877185560845529654126654085306143444318586769751456614068007002378776591344017127494704205622305389945613140711270004078547332699390814546646458807972708266830634328587856983052358089330657574067954571637752542021149557615814002501262285941302164715509792592309907965473761255176567513575178296664547791745011299614890304639947132962107340437518957359614589019389713111790429782856475032031986915140287080859904801094121472213179476477726224142548545403321571853061422881375850430633217518297986622371721591607716692547487389866549494501146540628433663937900397692656721463853067360965712091807638327166416274888800786925602902284721040317211860820419000422966171196377921337575114959501566049631862947265473642523081770367515906735023507283540567040386743513622224771589150495309844489333096340878"
				+ "076932599397805419341447377441842631298608099888687413260472156951623965864573021631598193195167353812974167729478672422924654366800980676928238280689964004824354037014163149658979409243237896907069779422362508221688957383798623001593776471651228935786015881617557829735233446042815126272037343146531977774160319906655418763979293344195215413418994854447345673831624993419131814809277771038638773431772075456545322077709212019051660962804909263601975988281613323166636528619326686336062735676303544776280350450777235547105859548702790814356240145171806246436267945612753181340783303362542327839449753824372058353114771199260638133467768796959703098339130771098704085913374641442822772634659470474587847787201927715280731767907707157213444730605700733492436931138350493163128404251219256517980694113528013147013047816437885185290928545201165839341965621349143415956258658655705526904965209858033850722426482939728584783163057777560688876446248246857926039535277348030480290058760758251047470916439613626760449256274204208320856611906254543372131535958450687724602901618766795240616342522577195429162991930645537799140373404328752628889639958794757291746426357455254079091451357111369410911939325191076020825202618798531887705842972591677813149699009019211697173727847684726860849003377024242916513005005168323364350389517029893922334517220138128069650117844087451960121228599371623130171144484640903890644954440061986907548516026327505298349187407866808818338510228334508504860825039302133219715518430635455007668282949304137765527939751754613953984683393638304746119966538581538420568533862186725233402830871123282789212507712629463229563989898935821167456270102183564622013496715188190973038119800497340723961036854066431939509790190699639552453005450580685501956730229219139339185680344903982059551002263535361920419947455385938102343955449597783779023742161727111723643435439478221818528624085140066604433258885698670543154706965747458550332323342107301545940516553790686627333799585115625784322988273723198987571415957811196358330059408730681216028764962867446047746491599505497374256269010490377819868359381465741268049256487985561453723478673303904688383436346553794986419270563872931748723320837601123029911367938627089438799362016295154133714248928307220126901475466847653576164773794675200490757155527819653621323926406160136358155907422020203187277605277219005561484255518792530343513984425322341576233610642506390497500865627109535919465897514131034822769306247435363256916078154781811528436679570"
				+ "61108615331504452127473924544945423682886061340841486377670096120715124914043027253860764823634143346235189757664521641376796903149501910857598442391986291642193994907236234646844117394032659184044378051333894525742399508296591228508555821572503107125701266830240292952522011872676756220415420516184163484756516999811614101002996078386909291603028840026910414079288621507842451670908700069928212066041837180653556725253256753286129104248776182582976515795984703562226293486003415872298053498965022629174878820273420922224533985626476691490556284250391275771028402799806636582548892648802545661017296702664076559042909945681506526530537182941270336931378517860904070866711496558343434769338578171138645587367812301458768712660348913909562009939361031029161615288138437909904231747336394804575931493140529763475748119356709110137751721008031559024853090669203767192203322909433467685142214477379393751703443661991040337511173547191855046449026365512816228824462575916333039107225383742182140883508657391771509682887478265699599574490661758344137522397096834080053559849175417381883999446974867626551658276584835884531427756879002909517028352971634456212964043523117600665101241200659755851276178583829204197484423608007193045761893234922927965019875187212726750798125547095890455635792122103334669749923563025494780249011419521238281530911407907386025152274299581807247162591668545133312394804947079119153267343028244186041426363954800044800267049624820179289647669758318327131425170296923488962766844032326092752496035799646925650493681836090032380929345958897069536534940603402166544375589004563288225054525564056448246515187547119621844396582533754388569094113031509526179378002974120766514793942590298969594699556576121865619673378623625612521632086286922210327488921865436480229678070576561514463204692790682120738837781423356282360896320806822246801224826117718589638140918390367367222088832151375560037279839400415297002878307667094447456013455641725437090697939612257142989467154357846878861444581231459357198492252847160504922124247014121478057345510500801908699603302763478708108175450119307141223390866393833952942578690507643100638351983438934159613185434754649556978103829309716465143840700707360411237359984345225161050702705623526601276484830840761183013052793205427462865403603674532865105706587488225698157936789766974220575059683440869735020141020672358502007245225632651341055924019027421624843914035998953539459094407046912091409387001264560016237428802109276457931065792295524988727584610126483699989225695968815920560010165525637567");
//		
		ArrayList<Integer> piBase12 = new ArrayList<Integer>();
		for(int i = 0; i<90; i++){
			piBase12.add(pi.intValue());
			pi = pi.subtract(new BigDecimal(pi.intValue()));
			pi = pi.multiply(new BigDecimal(12));
		}
		System.out.println(piBase12.toString());

		// 3.18480 9493B 91866 4573A 6211B B1515 51A05 72929 0A780 9A492 74214 0A60A 55256 A0661 A0375 3A3AA 54805 64688 0181A 36830

		ArrayList<Integer> length = new ArrayList<Integer>();
		ArrayList<Integer> tempArray = new ArrayList<Integer>();
		ArrayList<Boolean> tempArrayBooleansC = new ArrayList<Boolean>();
		ArrayList<Boolean> tempArrayBooleansG = new ArrayList<Boolean>();
		ArrayList<Boolean> tempArrayBooleansD = new ArrayList<Boolean>();
		ArrayList<Boolean> tempArrayBooleansA = new ArrayList<Boolean>();
		ArrayList<Boolean> tempArrayBooleansE = new ArrayList<Boolean>();
		ArrayList<Boolean> tempArrayBooleansB = new ArrayList<Boolean>();
		ArrayList<Boolean> tempArrayBooleansFSharp = new ArrayList<Boolean>();
		ArrayList<Boolean> tempArrayBooleansDFlat = new ArrayList<Boolean>();
		ArrayList<Boolean> tempArrayBooleansAFlat = new ArrayList<Boolean>();
		ArrayList<Boolean> tempArrayBooleansEFlat = new ArrayList<Boolean>();
		ArrayList<Boolean> tempArrayBooleansBFlat = new ArrayList<Boolean>();
		ArrayList<Boolean> tempArrayBooleansF = new ArrayList<Boolean>();
		
		int counter = 0;
		
		for(int vals:piBase12){

			tempArray.add(vals);
			for(int i = 0; i< tempArray.size(); i++){
				if(Tester.contains(c, tempArray.get(i))){
					tempArrayBooleansC.add(i, true);
				}
				else tempArrayBooleansC.add(i, false);
				if(Tester.contains(g, tempArray.get(i))){
					tempArrayBooleansG.add(i, true);
				}
				else tempArrayBooleansG.add(i, false);
				if(Tester.contains(d, tempArray.get(i))){
					tempArrayBooleansD.add(i, true);
				}
				else tempArrayBooleansD.add(i, false);
				if(Tester.contains(a, tempArray.get(i))){
					tempArrayBooleansA.add(i, true);
				}
				else tempArrayBooleansA.add(i, false);
				if(Tester.contains(e, tempArray.get(i))){
					tempArrayBooleansE.add(i, true);
				}
				else tempArrayBooleansE.add(i, false);
				if(Tester.contains(b, tempArray.get(i))){
					tempArrayBooleansB.add(i, true);
				}
				else tempArrayBooleansB.add(i, false);
				if(Tester.contains(fsharp, tempArray.get(i))){
					tempArrayBooleansFSharp.add(i, true);
				}
				else tempArrayBooleansFSharp.add(i, false);
				if(Tester.contains(dflat, tempArray.get(i))){
					tempArrayBooleansDFlat.add(i, true);
				}
				else tempArrayBooleansDFlat.add(i, false);
				if(Tester.contains(aflat, tempArray.get(i))){
					tempArrayBooleansAFlat.add(i, true);
				}
				else tempArrayBooleansAFlat.add(i, false);
				if(Tester.contains(eflat, tempArray.get(i))){
					tempArrayBooleansEFlat.add(i, true);
				}
				else tempArrayBooleansEFlat.add(i, false);
				if(Tester.contains(bflat, tempArray.get(i))){
					tempArrayBooleansBFlat.add(i, true);
				}
				else tempArrayBooleansBFlat.add(i, false);
				if(Tester.contains(f, tempArray.get(i))){
					tempArrayBooleansF.add(i, true);
				}
				else tempArrayBooleansF.add(i, false);
				
			}
			boolean[] allKeys = new boolean[12];
			if(Tester.allTrue(tempArrayBooleansC))
				allKeys[0] = true;
			else allKeys[0] = false;
			if(Tester.allTrue(tempArrayBooleansG))
				allKeys[1] = true;
			else allKeys[1] = false;
			if(Tester.allTrue(tempArrayBooleansD))
				allKeys[2] = true;
			else allKeys[2] = false;
			if(Tester.allTrue(tempArrayBooleansA))
				allKeys[3] = true;
			else allKeys[3] = false;
			if(Tester.allTrue(tempArrayBooleansE))
				allKeys[4] = true;
			else allKeys[4] = false;
			if(Tester.allTrue(tempArrayBooleansB))
				allKeys[5] = true;
			else allKeys[5] = false;
			if(Tester.allTrue(tempArrayBooleansFSharp))
				allKeys[6] = true;
			else allKeys[6] = false;
			if(Tester.allTrue(tempArrayBooleansDFlat))
				allKeys[7] = true;
			else allKeys[7] = false;
			if(Tester.allTrue(tempArrayBooleansAFlat))
				allKeys[8] = true;
			else allKeys[8] = false;
			if(Tester.allTrue(tempArrayBooleansEFlat))
				allKeys[9] = true;
			else allKeys[9] = false;
			if(Tester.allTrue(tempArrayBooleansBFlat))
				allKeys[10] = true;
			else allKeys[10] = false;
			if(Tester.allTrue(tempArrayBooleansF))
				allKeys[11] = true;
			else allKeys[11] = false;
			if(!Tester.allFalse(allKeys)){
				counter++;
			}
			else{
				length.add(counter);
				counter = 0;
				allKeys = new boolean[12];
				tempArrayBooleansC.clear();
				tempArrayBooleansG.clear();
				tempArrayBooleansD.clear();
				tempArrayBooleansA.clear();
				tempArrayBooleansE.clear();
				tempArrayBooleansB.clear();
				tempArrayBooleansFSharp.clear();
				tempArrayBooleansDFlat.clear();
				tempArrayBooleansAFlat.clear();
				tempArrayBooleansEFlat.clear();
				tempArrayBooleansBFlat.clear();
				tempArrayBooleansF.clear();
				tempArray.clear();
			}
			
		}
		
		System.out.println();

		int sum = 0;
		for(int vals:length)
			sum+=vals;
		double avg = (double)sum / length.size();
		System.out.println(avg);
	}
}
