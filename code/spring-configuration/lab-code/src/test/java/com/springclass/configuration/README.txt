##---------------------------------------------------------------------------##
## NOTES
##---------------------------------------------------------------------------##

JavaConfig.java
---------------


    @Value("BUR-1")
    private String location;


    @Autowired
    @Qualifier("airportLocationDAOBean")
    private AirportLocationDAO airportLocationDAO;

    @Autowired
    @Qualifier("dvdInfoDAOBean")
    private DVDInfoDAO dvdInfoDAO;

    @Autowired
    @Qualifier("dvdLocationDAOBean")
    private DVDLocationDAO dvdLocationDAO;

    @Autowired
    @Qualifier("loanDAOBean")
    private LoanDAO loanDAO;

    @Autowired
    @Qualifier("memberDAOBean")
    MemberDAO memberDAO;






StubConfig.java
---------------
    @Bean
    public AirportLocationDAO airportLocationDAOBean(){
        return new AirportLocationDAOImpl();
    }

    @Bean
    public DVDInfoDAO dvdInfoDAOBean(){
        return new DVDInfoDAOImpl();
    }

    @Bean
    public DVDLocationDAO dvdLocationDAOBean(){
        return new DVDLocationDAOImpl();
    }

    @Bean
    public LoanDAO loanDAOBean(){
        return new LoanDAOImpl();
    }

    @Bean
    public MemberDAO memberDAOBean(){
        return new MemberDAOImpl();
    }





##---------------------------------------------------------------------------##
