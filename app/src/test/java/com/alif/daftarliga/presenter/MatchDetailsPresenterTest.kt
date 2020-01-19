package com.alif.daftarliga.presenter

import com.alif.daftarliga.utilities.TestContextProvider
import com.alif.daftarliga.model.Event
import com.alif.daftarliga.model.EventResponse
import com.alif.daftarliga.model.Team
import com.alif.daftarliga.model.TeamResponse
import com.alif.daftarliga.model.webservice.ApiRepository
import com.alif.daftarliga.view.viewinterfaces.MatchDetailsView
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Before
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MatchDetailsPresenterTest {
    @Mock
    private lateinit var matchDetailsView: MatchDetailsView

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var matchDetailsPresenter: MatchDetailsPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        matchDetailsPresenter = MatchDetailsPresenter(
            matchDetailsView, apiRepository, gson, TestContextProvider()
        )
    }

    @Test
    fun testGetMatchDetailsData() {
        // NOTE: to initialize means creating a mock here
        // initialize idEvent
        val idEvent: String? = "610504"
        // initialize idHomeTeam
        val idHomeTeam: String? = "133604"
        // initialize idAwayTeam
        val idAwayTeam: String? = "133610"
        // initialize matchDetails (event)
        val matchDetails = Event("610504",null,null,"Arsenal vs Chelsea",
            "Chelsea @ Arsenal","FA Community Shield 2015-08-02 Arsenal vs Chelsea",
            "Soccer","4571","FA Community Shield","2015",
            "2015 FA Community Shield (also known as The FA Community Shield supported by McDonald's for sponsorship reasons) was the 93rd FA Community Shield, an annual English football match played between the winners of the previous season's Premier League and FA Cup. The match was contested by Arsenal, the 2014–15 FA Cup winners, and Chelsea, champions of the 2014–15 Premier League. It was held at Wembley Stadium on 2 August 2015.",
            "Arsenal","Chelsea","1","0","0","85437",
            null,null,null,null,null,
            null,null,null,null,null,
            null,null,null,null,null,
            null,null,null,null,null,
            "2015-08-02",null,null,"00:00:00",null,null,
            "133604","133610",
            "Watched by a crowd of 85,437 and a television audience of over a million, Arsenal won the match 1–0. \r\nThe only goal of the match came near the midway point of the first half; Alex Oxlade-Chamberlain collected a pass from Theo Walcott from the right and scored past Thibaut Courtois. Both managers did not shake hands after the game, which drew media attention. Arsenal's victory was their first against Chelsea in over three years and manager Arsène Wenger's first over José Mourinho in 14 attempts. ",
            null,null,null,"https://www.thesportsdb.com/images/media/event/poster/pjjo191565436756.jpg",
            null,null,null,null,"","","","","unlocked")
        // initialize homeTeam
        val homeTeam = Team("133604","9","42","2","Arsenal","Ars","Gunners",
            "1892","Soccer","English Premier League","4328",null,"",
            "Emirates Stadium","Gunners, Gooners","https://www.allarsenal.com/feed/",
            "https://www.thesportsdb.com/images/media/team/stadium/qpuxrr1419371354.jpg",
            "The Emirates Stadium (known as Ashburton Grove prior to sponsorship) is a football stadium in Holloway, London, England, and the home of Arsenal Football Club. With a capacity of 60,272, the Emirates is the third-largest football stadium in England after Wembley and Old Trafford.In 1997, Arsenal explored the possibility of relocating to a new stadium, having been denied planning permission by Islington Council to expand its home ground of Highbury. After considering various options (including purchasing Wembley), the club bought an industrial and waste disposal estate in Ashburton Grove in 2000. A year later they won the council's approval to build a stadium on the site; manager Arsène Wenger described this as the \"biggest decision in Arsenal's history\" since the board appointed Herbert Chapman. Relocation began in 2002, but financial difficulties delayed work until February 2004. Emirates Airline was later announced as the main sponsor for the stadium. Work was completed in 2006 at a cost of £390 million.",
            "Holloway, London","60338","www.arsenal.com","www.facebook.com/Arsenal",
            "twitter.com/arsenal","instagram.com/arsenal",
            "Arsenal Football Club is a professional football club based in Holloway, London which currently plays in the Premier League, the highest level of English football. One of the most successful clubs in English football, they have won 13 First Division and Premier League titles and a joint record 11 FA Cups.\r\n\r\nArsenal's success has been particularly consistent: the club has accumulated the second most points in English top-flight football, hold the ongoing record for the longest uninterrupted period in the top flight, and would be placed first in an aggregated league of the entire 20th century. Arsenal is the second side to complete an English top-flight season unbeaten (in the 2003–04 season), playing almost twice as many matches as the previous invincibles Preston North End in the 1888–89 season.\r\n\r\nArsenal was founded in 1886 in Woolwich and in 1893 became the first club from the south of England to join the Football League. In 1913, they moved north across the city to Arsenal Stadium in Highbury. In the 1930s, they won five League Championship titles and two FA Cups. After a lean period in the post-war years they won the League and FA Cup Double, in the 1970–71 season, and in the 1990s and first decade of the 21st century, won two more Doubles and reached the 2006 UEFA Champions League Final. Since neighbouring Tottenham Hotspur, the two clubs have had a fierce rivalry, the North London derby.\r\n\r\nArsenal have one of the highest incomes and largest fanbases in the world. The club was named the fifth most valuable association football club in the world, valued at £1.3 billion in 2014.",
            "Der FC Arsenal (offiziell: Arsenal Football Club) – auch bekannt als (The) Arsenal, (The) Gunners (deutsche Übersetzung: „Schützen“ oder „Kanoniere“) oder im deutschen Sprachraum auch Arsenal London genannt – ist ein 1886 gegründeter Fußballverein aus dem Ortsteil Holloway des Nordlondoner Bezirks Islington. Mit 13 englischen Meisterschaften und elf FA-Pokalsiegen zählt der Klub zu den erfolgreichsten englischen Fußballvereinen.Erst über 40 Jahre nach der Gründung gewann Arsenal mit fünf Ligatiteln und zwei FA Cups in den 1930er Jahren seine ersten bedeutenden Titel. Der nächste Meilenstein war in der Saison 1970/71 der Gewinn des zweiten englischen „Doubles“ im 20. Jahrhundert. In den vergangenen 20 Jahren etablierte sich Arsenal endgültig als einer der erfolgreichsten englischen Fußballvereine, und beim Gewinn zweier weiterer Doubles zu Beginn des 21. Jahrhunderts blieb die Mannschaft in der Ligasaison 2003/04 als zweite in der englischen Fußballgeschichte ungeschlagen. Zunehmende europäische Ambitionen unterstrich der Verein in der Spielzeit 2005/06, als Arsenal als erster Londoner Verein das Finale der Champions League erreichte.",
            null, null,
            "L'Arsenal Football Club, noto semplicemente come Arsenal, è una società calcistica inglese con sede a Londra, più precisamente nel quartiere di Holloway, nel borgo di Islington.[3]\r\n\r\nFondato nel 1886, è uno dei quattordici club che rappresentano la città di Londra a livello professionistico,[4] nonché uno dei più antichi del Paese. Milita nella massima serie del calcio inglese ininterrottamente dal 1919-1920, risultando quindi la squadra da più tempo presente in First Division/Premier League. È la prima squadra della capitale del Regno Unito per successi sportivi e, in ambito federale, la terza dopo Manchester United e Liverpool, essendosi aggiudicata nel corso della sua storia tredici campionati inglesi, dodici FA Cup (record di vittorie, condiviso con il Manchester United), due League Cup e quattordici Community Shield (una condivisa),[5] mentre in ambito internazionale ha conquistato una Coppa delle Coppe ed una Coppa delle Fiere. Inoltre è una delle tredici squadre che hanno raggiunto le finali di tutte le tre principali competizioni gestite dalla UEFA: Champions League (2005-2006), Coppa UEFA (1999-2000) e Coppa delle Coppe (1979-1980, 1993-1994 e 1994-1995).[6]\r\n\r\nI colori sociali, rosso per la maglietta e bianco per i pantaloncini, hanno subìto variazioni più o meno evidenti nel corso degli anni. Anche la sede del club è stata cambiata più volte: inizialmente la squadra giocava a Woolwich, ma nel 1913 si spostò all'Arsenal Stadium, nel quartiere di Highbury; dal 2006 disputa invece le sue partite casalinghe nel nuovo Emirates Stadium. Lo stemma è stato modificato ripetutamente, ma al suo interno è sempre comparso almeno un cannone. Proprio per questo motivo i giocatori ed i tifosi dell'Arsenal sono spesso soprannominati Gunners (in italiano \"cannonieri\").\r\n\r\nL'Arsenal conta su una schiera di tifosi molto nutrita, distribuita in ogni parte del mondo. Nel corso degli anni sono sorte profonde rivalità con i sostenitori di club concittadini, la più sentita delle quali è quella con i seguaci del Tottenham Hotspur, con i quali i Gunners giocano regolarmente il North London derby.[7] L'Arsenal è anche uno dei club più ricchi del mondo, con un patrimonio stimato di 1,3 miliardi di dollari, secondo la rivista Forbes nel 2014, facendone il quinto club più ricco del pianeta e il secondo in Inghilterra.[8]",
            null,null,null,null,null,null,null,null,null,null,
            "Male","England",
            "https://www.thesportsdb.com/images/media/team/badge/a1af2i1557005128.png",
            "https://www.thesportsdb.com/images/media/team/jersey/2019-133604-Jersey.png",
            "https://www.thesportsdb.com/images/media/team/logo/q2mxlz1512644512.png",
            "https://www.thesportsdb.com/images/media/team/fanart/xyusxr1419347566.jpg",
            "https://www.thesportsdb.com/images/media/team/fanart/qttspr1419347612.jpg",
            "https://www.thesportsdb.com/images/media/team/fanart/uwssqx1420884450.jpg",
            "https://www.thesportsdb.com/images/media/team/fanart/qtprsw1420884964.jpg",
            "https://www.thesportsdb.com/images/media/team/banner/rtpsrr1419351049.jpg",
            "www.youtube.com/user/ArsenalTour",
            "unlocked")
        // initialize awayTeam
        val awayTeam = Team("133610","15","49","3","Chelsea",null,"",
            "1905","Soccer","English Premier League","4328",null,"",
            "Stamford Bridge","","http://feeds.feedburner.com/chelseafc/latestnews",
            "https://www.thesportsdb.com/images/media/team/stadium/vpuxsx1420200157.jpg",
            "Stamford Bridge (/ˈstæm.fərd ˈbrɪdʒ/) is a football stadium located in Fulham, London. It is the home ground of Chelsea F.C.. The stadium is located within the Moore Park Estate also known as Walham Green and is often referred to as simply The Bridge. The capacity is 41,798, making it the eighth largest ground in the Premier League.\r\n\r\nOpened in 1877, the stadium was used by the London Athletic Club until 1905, when new owner Gus Mears founded Chelsea Football Club to occupy the ground; Chelsea have played their home games there ever since. It has undergone numerous major changes over the years, most recently in the 1990s when it was renovated into a modern, all-seater stadium.\r\n\r\nStamford Bridge has been used as a venue for England international matches, FA Cup Finals, FA Cup semi-finals and Charity Shield games. It has also hosted numerous other sports, such as cricket, rugby union, speedway, greyhound racing, baseball and American football. The stadium's highest official attendance is 82,905, for a league match between Chelsea and Arsenal on 12 October 1935.",
            "Fulham, London","41798","www.chelseafc.com","www.facebook.com/ChelseaFC",
            "twitter.com/chelseafc","www.instagram.com/chelseafc",
            "Chelsea Football Club /ˈtʃɛlsiː/ are a professional football club based in Fulham, London, who play in the Premier League, the highest level of English football. Founded in 1905, the club have spent most of their history in the top tier of English football. The club's home ground is the 41,837-seat Stamford Bridge stadium, where they have played since their establishment.\r\n\r\nChelsea had their first major success in 1955, winning the league championship, and won various cup competitions during the 1960s, 1970s, 1990s and 2000s. The club have enjoyed their greatest period of success in the past two decades, winning 15 major trophies since 1997. Domestically, Chelsea have won four league titles, seven FA Cups, four League Cups and four FA Community Shields, while in continental competitions they have won two UEFA Cup Winners' Cups, one UEFA Super Cup, one UEFA Europa League and one UEFA Champions League. Chelsea are the only London club to win the UEFA Champions League, and one of four clubs, and the only British club, to have won all three main UEFA club competitions.\r\n\r\nChelsea's regular kit colours are royal blue shirts and shorts with white socks. The club's crest has been changed several times in attempts to re-brand the club and modernise its image. The current crest, featuring a ceremonial lion rampant regardant holding a staff, is a modification of the one introduced in the early 1950s. The club have sustained the fifth highest average all-time attendance in English football. Their average home gate for the 2012–13 season was 41,462, the sixth highest in the Premier League. Since July 2003, Chelsea have been owned by Russian billionaire Roman Abramovich. In April 2013 they were ranked by Forbes Magazine as the seventh most valuable football club in the world, at £588 million ($901 million), an increase of 18% from the previous year.",
            null,null,null,null,null,null,
            null,null,null,null,null,null,
            null,null,"Male","England",
            "https://www.thesportsdb.com/images/media/team/badge/yvwvtu1448813215.png",
            "https://www.thesportsdb.com/images/media/team/jersey/2019-133610-Jersey.png",
            "https://www.thesportsdb.com/images/media/team/logo/urupss1421777612.png",
            "https://www.thesportsdb.com/images/media/team/fanart/rppwtt1424447399.jpg",
            "https://www.thesportsdb.com/images/media/team/fanart/qtqrus1424447422.jpg",
            "https://www.thesportsdb.com/images/media/team/fanart/tws3yo1548279989.jpg",
            "https://www.thesportsdb.com/images/media/team/fanart/wwuput1421778515.jpg",
            "https://www.thesportsdb.com/images/media/team/banner/twxrxv1421778197.jpg",
            "www.youtube.com/user/chelseafc","unlocked")

        /*match details*/
        // create eventList and add an event (matchDetails) to it
        val eventList: MutableList<Event> = mutableListOf()
        eventList.add(matchDetails)
        // initialize eventResponse
        // eventResponse consists of list of events
        val eventResponse = EventResponse(eventList)

        /*home team*/
        // create homeTeamList and add a team to it
        val homeTeamList: MutableList<Team> = mutableListOf()
        homeTeamList.add(homeTeam)
        // initialize homeTeamResponse
        // homeTeamResponse consists of list of teams
        val homeTeamResponse = TeamResponse(homeTeamList)

        /*away team*/
        // create homeTeamList and add a team to it
        val awayTeamList: MutableList<Team> = mutableListOf()
        awayTeamList.add(awayTeam)
        // initialize homeTeamResponse
        // homeTeamResponse consists of list of teams
        val awayTeamResponse = TeamResponse(awayTeamList)

        // initialize homeTeamImage
        val homeTeamImage: String? = homeTeam.strTeamBadge
        // initialize awayTeamImage
        val awayTeamImage: String? = awayTeam.strTeamBadge

        runBlocking {
            // getMatchDetails
            Mockito.`when`(
                apiRepository.doRequestWithCoroutinesAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(gson.fromJson("", EventResponse::class.java))
                .thenReturn(eventResponse)

            // getHomeTeamDetails to get homeTeam badge
            Mockito.`when`(
                apiRepository.doRequestWithCoroutinesAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(gson.fromJson("", TeamResponse::class.java))
                .thenReturn(homeTeamResponse).thenReturn(awayTeamResponse)

            matchDetailsPresenter.getMatchDetailsData(idEvent, idHomeTeam, idAwayTeam)

            Mockito.verify(matchDetailsView).showLoading()
            Mockito.verify(matchDetailsView).hideLoading()
            Mockito.verify(matchDetailsView)
                .showMatchDetails(matchDetails, homeTeamImage, awayTeamImage)
        }
    }
}