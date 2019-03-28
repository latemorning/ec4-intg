package com.ec4.integration;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.remote.session.CachingSessionFactory;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.integration.sftp.dsl.Sftp;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;

import com.jcraft.jsch.ChannelSftp.LsEntry;


@SpringBootApplication
@PropertySource("classpath:/sftp.properties")
public class IntegrationApplication {

    @Value("${sftp.host}")
    private String sftpHost;

    @Value("${sftp.port}")
    private int sftpPort;

    @Value("${sftp.user}")
    private String sftpUser;

    @Value("${sftp.privateKey:#{null}}")
    private Resource sftpPrivateKey;

    @Value("${sftp.privateKeyPassphrase:}")
    private String sftpPrivateKeyPassphrase;

    @Value("${sftp.password}")
    private String sftpPasword;

    @Value("${sftp.remote.directory.download.filter}")
    private String sftpRemoteDirectoryDownloadFilter;

    @Value("${sftp.remote.directory.as}")
    private String sftpRemoteDirectoryAs;

//    @Value("${sftp.remote.directory.car}")
//    private String sftpRemoteDirectoryCar;

    @Value("${sftp.remote.directory.cust}")
    private String sftpRemoteDirectoryCust;

    @Value("${sftp.remote.directory.user}")
    private String sftpRemoteDirectoryUser;

    @Value("${sftp.remote.directory.replc}")
    private String sftpRemoteDirectoryReplc;

    @Value("${sftp.remote.directory.bhfLc}")
    private String sftpRemoteDirectoryBhfLc;

    @Value("${sftp.remote.directory.buzplc}")
    private String sftpRemoteDirectoryBuzplc;

    @Value("${sftp.remote.directory.saleCar}")
    private String sftpRemoteDirectorySaleCar;

    @Value("${sftp.remote.directory.mntncCar}")
    private String sftpRemoteDirectoryMntncCar;

    @Value("${sftp.remote.directory.mntncCstmr}")
    private String sftpRemoteDirectoryMntncCstmr;

    @Value("${sftp.remote.directory.delivy}")
    private String sftpRemoteDirectoryDelivy;

    @Value("${sftp.remote.directory.hpcl}")
    private String sftpRemoteDirectoryHpcl;

    @Value("${sftp.remote.directory.recall}")
    private String sftpRemoteDirectoryRecall;

    @Value("${sftp.remote.directory.recallVhcle}")
    private String sftpRemoteDirectoryRecallVhcle;

    @Value("${sftp.remote.directory.recptnReject}")
    private String sftpRemoteDirectoryRecptnReject;

    @Value("${sftp.remote.directory.marktPrcuseAgre}")
    private String sftpRemoteDirectoryMarktPrcuseAgre;

    @Value("${sftp.remote.directory.bycstmAgre}")
    private String sftpRemoteDirectoryBycstmAgre;

    @Value("${sftp.remote.directory.cstmrAgreCode}")
    private String sftpRemoteDirectoryCstmrAgreCode;

    @Value("${sftp.remote.directory.bycntcAgre}")
    private String sftpRemoteDirectoryBycntcAgre;

    @Value("${sftp.remote.directory.korOwnersFlag}")
    private String sftpRemoteDirectoryKorOwnersFlag;

    @Value("${sftp.remote.directory.korOwnersFlagVin}")
    private String sftpRemoteDirectoryKorOwnersFlagVin;

    @Value("${sftp.remote.directory.korOwnersMember}")
    private String sftpRemoteDirectoryKorOwnersMember;


    @Value("${sftp.local.directory.as}")
    private String sftpLocalDirectoryAs;

//    @Value("${sftp.local.directory.car}")
//    private String sftpLocalDirectoryCar;

    @Value("${sftp.local.directory.cust}")
    private String sftpLocalDirectoryCust;

    @Value("${sftp.local.directory.user}")
    private String sftpLocalDirectoryUser;

    @Value("${sftp.local.directory.replc}")
    private String sftpLocalDirectoryReplc;

    @Value("${sftp.local.directory.bhfLc}")
    private String sftpLocalDirectoryBhfLc;

    @Value("${sftp.local.directory.buzplc}")
    private String sftpLocalDirectoryBuzplc;

    @Value("${sftp.local.directory.saleCar}")
    private String sftpLocalDirectorySaleCar;

    @Value("${sftp.local.directory.mntncCar}")
    private String sftpLocalDirectoryMntncCar;

    @Value("${sftp.local.directory.mntncCstmr}")
    private String sftpLocalDirectoryMntncCstmr;

    @Value("${sftp.local.directory.delivy}")
    private String sftpLocalDirectoryDelivy;

    @Value("${sftp.local.directory.hpcl}")
    private String sftpLocalDirectoryHpcl;

    @Value("${sftp.local.directory.recall}")
    private String sftpLocalDirectoryRecall;

    @Value("${sftp.local.directory.recallVhcle}")
    private String sftpLocalDirectoryRecallVhcle;

    @Value("${sftp.local.directory.recptnReject}")
    private String sftpLocalDirectoryRecptnReject;

    @Value("${sftp.local.directory.marktPrcuseAgre}")
    private String sftpLocalDirectoryMarktPrcuseAgre;

    @Value("${sftp.local.directory.bycstmAgre}")
    private String sftpLocalDirectoryBycstmAgre;

    @Value("${sftp.local.directory.cstmrAgreCode}")
    private String sftpLocalDirectoryCstmrAgreCode;

    @Value("${sftp.local.directory.bycntcAgre}")
    private String sftpLocalDirectoryBycntcAgre;

    @Value("${sftp.local.directory.korOwnersFlag}")
    private String sftpLocalDirectoryKorOwnersFlag;

    @Value("${sftp.local.directory.korOwnersFlagVin}")
    private String sftpLocalDirectoryKorOwnersFlagVin;

    @Value("${sftp.local.directory.korOwnersMember}")
    private String sftpLocalDirectoryKorOwnersMember;


    @Bean
    public SessionFactory<LsEntry> sftpSessionFactory() {

        DefaultSftpSessionFactory factory = new DefaultSftpSessionFactory(true);

        factory.setHost(sftpHost);
        factory.setPort(sftpPort);
        factory.setUser(sftpUser);
        factory.setPassword(sftpPasword);
        factory.setAllowUnknownKeys(true);
        //factory.setTestSession(true);

        return new CachingSessionFactory<LsEntry>(factory);
    }


//  @Bean
//  public SftpInboundFileSynchronizer sftpInboundFileSynchronizer1() {
//
//      SftpInboundFileSynchronizer fileSynchronizer = new SftpInboundFileSynchronizer(sftpSessionFactory());
//
//      fileSynchronizer.setDeleteRemoteFiles(true);
//      fileSynchronizer.setRemoteDirectory(sftpRemoteDirectoryUser);
//      fileSynchronizer.setFilter(new SftpSimplePatternFileListFilter(sftpRemoteDirectoryDownloadFilter));
//      return fileSynchronizer;
//  }
//
//
//  @Bean
//  @InboundChannelAdapter(channel = "fromSftpChannel1", poller = @Poller(cron = "0/10 * * * * *") )
//  public MessageSource<File> sftpMessageSource1() {
//
//      SftpInboundFileSynchronizingMessageSource source = new SftpInboundFileSynchronizingMessageSource(
//              sftpInboundFileSynchronizer1());
//      source.setLocalDirectory(new File(sftpLocalDirectoryUser));
//      source.setAutoCreateLocalDirectory(true);
//      source.setLocalFilter(new AcceptOnceFileListFilter<File>());
//      return source;
//  }
//
//
//  @Bean
//  @ServiceActivator(inputChannel = "fromSftpChannel1")
//  public MessageHandler resultFileHandler1() {
//
//      return new MessageHandler() {
//          @Override
//          public void handleMessage(Message<?> message) throws MessagingException {
//
//              System.err.println(message.getPayload() + " | handler1");
//          }
//      };
//  }

//
//    @Bean
//    public RotatingServerAdvice advice() {
//        List<KeyDirectory> keyDirectories = new ArrayList<>();
//        keyDirectories.add(new KeyDirectory("one", "foo"));
//        keyDirectories.add(new KeyDirectory("one", "bar"));
//        keyDirectories.add(new KeyDirectory("two", "baz"));
//        keyDirectories.add(new KeyDirectory("two", "qux"));
//        keyDirectories.add(new KeyDirectory("three", "fiz"));
//        keyDirectories.add(new KeyDirectory("three", "buz"));
//        return new RotatingServerAdvice(delegatingSf(), keyDirectories);
//    }


    @Bean
    public IntegrationFlow sftpInboundFlow() {

        return IntegrationFlows
            .from(Sftp.inboundAdapter( this.sftpSessionFactory() )
                    .preserveTimestamp(true)
                    .remoteDirectory(sftpRemoteDirectoryUser)
                    .regexFilter(".*\\.csv$")
                    //.localFilenameExpression("#this.toUpperCase() + '.a'")
                    .localDirectory(new File(sftpLocalDirectoryUser)),
                 e -> e.id("sftpInboundAdapterUser")
                    .autoStartup(true)
                    .poller(Pollers.fixedDelay(10000)))
            .handle(m -> System.out.println(m.getPayload() + " | handler1" ))
            .get();
    }


//    @Bean
//    public IntegrationFlow sftpInboundFlow2() {
//
//        return IntegrationFlows
//            .from(Sftp.inboundAdapter(this.sftpSessionFactory())
//                    .preserveTimestamp(true)
//                    .remoteDirectory(sftpRemoteDirectoryCar)
//                    .regexFilter(".*\\.csv$")
//                    //.localFilenameExpression("#this.toUpperCase() + '.a'")
//                    .localDirectory(new File(sftpLocalDirectoryCust)),
//                 e -> e.id("sftpInboundAdapterCar")
//                    .autoStartup(true)
//                    .poller(Pollers.fixedDelay(10000)))
//            .handle(m -> System.out.println(m.getPayload() + " | handler2" ))
//            .get();
//    }


    /**
     * 정비소 정보
     *
     * @return
     */
    @Bean
    public IntegrationFlow sftpInboundReplcFlow() {

        return IntegrationFlows
            .from(Sftp.inboundAdapter(this.sftpSessionFactory())
                    .preserveTimestamp(true)
                    .remoteDirectory(sftpRemoteDirectoryReplc)
                    .regexFilter(".*\\.txt$")
                    //.localFilenameExpression("#this.toUpperCase() + '.a'")
                    .localDirectory(new File(sftpLocalDirectoryReplc)),
                 e -> e.id("sftpInboundAdapterReplc")
                    .autoStartup(true)
                    .poller(Pollers.fixedDelay(10000)))
            .handle(m -> System.out.println(m.getPayload() + " | handler replc" ))
            .get();
    }


    /**
     * 지점 위치 정보
     *
     * @return
     */
    @Bean
    public IntegrationFlow sftpInboundBhfLcFlow() {

        return IntegrationFlows
            .from(Sftp.inboundAdapter(this.sftpSessionFactory())
                    .preserveTimestamp(true)
                    .remoteDirectory(sftpRemoteDirectoryBhfLc)
                    .regexFilter(".*\\.txt$")
                    //.localFilenameExpression("#this.toUpperCase() + '.a'")
                    .localDirectory(new File(sftpLocalDirectoryBhfLc)),
                 e -> e.id("sftpInboundAdapterBhfLc")
                    .autoStartup(true)
                    .poller(Pollers.fixedDelay(10000)))
            .handle(m -> System.out.println(m.getPayload() + " | handler bhfLc" ))
            .get();
    }


    /**
     * 영업소 정보
     *
     * @return
     */
    @Bean
    public IntegrationFlow sftpInboundBuzplcFlow() {

        return IntegrationFlows
            .from(Sftp.inboundAdapter(this.sftpSessionFactory())
                    .preserveTimestamp(true)
                    .remoteDirectory(sftpRemoteDirectoryBuzplc)
                    .regexFilter(".*\\.txt$")
                    //.localFilenameExpression("#this.toUpperCase() + '.a'")
                    .localDirectory(new File(sftpLocalDirectoryBuzplc)),
                 e -> e.id("sftpInboundAdapterBuzplc")
                    .autoStartup(true)
                    .poller(Pollers.fixedDelay(10000)))
            .handle(m -> System.out.println(m.getPayload() + " | handler Buzplc" ))
            .get();
    }


    /**
     * 차량 마스타(세일즈)
     *
     * @return
     */
    @Bean
    public IntegrationFlow sftpInboundSaleCarFlow() {

        return IntegrationFlows
            .from(Sftp.inboundAdapter(this.sftpSessionFactory())
                    .preserveTimestamp(true)
                    .remoteDirectory(sftpRemoteDirectorySaleCar)
                    .regexFilter(".*\\.txt$")
                    //.localFilenameExpression("#this.toUpperCase() + '.a'")
                    .localDirectory(new File(sftpLocalDirectorySaleCar)),
                 e -> e.id("sftpInboundAdapterSaleCar")
                    .autoStartup(true)
                    .poller(Pollers.fixedDelay(10000)))
            .handle(m -> System.out.println(m.getPayload() + " | handler SaleCar" ))
            .get();
    }


    /**
     * 차량 마스타(정비)
     *
     * @return
     */
    @Bean
    public IntegrationFlow sftpInboundMntncCarFlow() {

        return IntegrationFlows
                .from(Sftp.inboundAdapter(this.sftpSessionFactory())
                        .preserveTimestamp(true)
                        .remoteDirectory(sftpRemoteDirectoryMntncCar)
                        .regexFilter(".*\\.txt$")
                        //.localFilenameExpression("#this.toUpperCase() + '.a'")
                        .localDirectory(new File(sftpLocalDirectoryMntncCar)),
                        e -> e.id("sftpInboundAdapterMntncCar")
                        .autoStartup(true)
                        .poller(Pollers.fixedDelay(10000)))
                .handle(m -> System.out.println(m.getPayload() + " | handler MntncCar" ))
                .get();
    }


    /**
     * 고객 마스타(정비)
     *
     * @return
     */
    @Bean
    public IntegrationFlow sftpInboundMntncCstmrFlow() {

        return IntegrationFlows
                .from(Sftp.inboundAdapter(this.sftpSessionFactory())
                        .preserveTimestamp(true)
                        .remoteDirectory(sftpRemoteDirectoryMntncCstmr)
                        .regexFilter(".*\\.txt$")
                        //.localFilenameExpression("#this.toUpperCase() + '.a'")
                        .localDirectory(new File(sftpLocalDirectoryMntncCstmr)),
                        e -> e.id("sftpInboundAdapterMntncCstmr")
                        .autoStartup(true)
                        .poller(Pollers.fixedDelay(10000)))
                .handle(m -> System.out.println(m.getPayload() + " | handler MntncCstmr" ))
                .get();
    }


    /**
     * 출고
     *
     * @return
     */
    @Bean
    public IntegrationFlow sftpInboundDelivyFlow() {

        return IntegrationFlows
                .from(Sftp.inboundAdapter(this.sftpSessionFactory())
                        .preserveTimestamp(true)
                        .remoteDirectory(sftpRemoteDirectoryDelivy)
                        .regexFilter(".*\\.txt$")
                        //.localFilenameExpression("#this.toUpperCase() + '.a'")
                        .localDirectory(new File(sftpLocalDirectoryDelivy)),
                        e -> e.id("sftpInboundAdapterDelivy")
                        .autoStartup(true)
                        .poller(Pollers.fixedDelay(10000)))
                .handle(m -> System.out.println(m.getPayload() + " | handler Delivy" ))
                .get();
    }


    /**
     * 해피콜
     *
     * @return
     */
    @Bean
    public IntegrationFlow sftpInboundHpclFlow() {

        return IntegrationFlows
                .from(Sftp.inboundAdapter(this.sftpSessionFactory())
                        .preserveTimestamp(true)
                        .remoteDirectory(sftpRemoteDirectoryHpcl)
                        .regexFilter(".*\\.txt$")
                        //.localFilenameExpression("#this.toUpperCase() + '.a'")
                        .localDirectory(new File(sftpLocalDirectoryHpcl)),
                        e -> e.id("sftpInboundAdapterHpcl")
                        .autoStartup(true)
                        .poller(Pollers.fixedDelay(10000)))
                .handle(m -> System.out.println(m.getPayload() + " | handler Hpcl" ))
                .get();
    }


    /**
     * 리콜
     *
     * @return
     */
    @Bean
    public IntegrationFlow sftpInboundRecallFlow() {

        return IntegrationFlows
                .from(Sftp.inboundAdapter(this.sftpSessionFactory())
                        .preserveTimestamp(true)
                        .remoteDirectory(sftpRemoteDirectoryRecall)
                        .regexFilter(".*\\.txt$")
                        //.localFilenameExpression("#this.toUpperCase() + '.a'")
                        .localDirectory(new File(sftpLocalDirectoryRecall)),
                        e -> e.id("sftpInboundAdapterRecall")
                        .autoStartup(true)
                        .poller(Pollers.fixedDelay(10000)))
                .handle(m -> System.out.println(m.getPayload() + " | handler Recall" ))
                .get();
    }


    /**
     * 리콜차량
     *
     * @return
     */
    @Bean
    public IntegrationFlow sftpInboundRecallVhcleFlow() {

        return IntegrationFlows
                .from(Sftp.inboundAdapter(this.sftpSessionFactory())
                        .preserveTimestamp(true)
                        .remoteDirectory(sftpRemoteDirectoryRecallVhcle)
                        .regexFilter(".*\\.txt$")
                        //.localFilenameExpression("#this.toUpperCase() + '.a'")
                        .localDirectory(new File(sftpLocalDirectoryRecallVhcle)),
                        e -> e.id("sftpInboundAdapterRecallVhcle")
                        .autoStartup(true)
                        .poller(Pollers.fixedDelay(10000)))
                .handle(m -> System.out.println(m.getPayload() + " | handler RecallVhcle" ))
                .get();
    }


    /**
     * 수신거부
     *
     * @return
     */
    @Bean
    public IntegrationFlow sftpInboundRecptnRejectFlow() {

        return IntegrationFlows
                .from(Sftp.inboundAdapter(this.sftpSessionFactory())
                        .preserveTimestamp(true)
                        .remoteDirectory(sftpRemoteDirectoryRecptnReject)
                        .regexFilter(".*\\.txt$")
                        //.localFilenameExpression("#this.toUpperCase() + '.a'")
                        .localDirectory(new File(sftpLocalDirectoryRecptnReject)),
                        e -> e.id("sftpInboundAdapterRecptnReject")
                        .autoStartup(true)
                        .poller(Pollers.fixedDelay(10000)))
                .handle(m -> System.out.println(m.getPayload() + " | handler RecptnReject" ))
                .get();
    }


    /**
     * 정비 마케팅활용동의 고객동의
     *
     * @return
     */
    @Bean
    public IntegrationFlow sftpInboundMarktPrcuseAgreFlow() {

        return IntegrationFlows
                .from(Sftp.inboundAdapter(this.sftpSessionFactory())
                        .preserveTimestamp(true)
                        .remoteDirectory(sftpRemoteDirectoryMarktPrcuseAgre)
                        .regexFilter(".*\\.txt$")
                        //.localFilenameExpression("#this.toUpperCase() + '.a'")
                        .localDirectory(new File(sftpLocalDirectoryMarktPrcuseAgre)),
                        e -> e.id("sftpInboundAdapterMarktPrcuseAgre")
                        .autoStartup(true)
                        .poller(Pollers.fixedDelay(10000)))
                .handle(m -> System.out.println(m.getPayload() + " | handler MarktPrcuseAgre" ))
                .get();
    }


    /**
     * sales 고객별동의
     *
     * @return
     */
    @Bean
    public IntegrationFlow sftpInboundBycstmAgreFlow() {

        return IntegrationFlows
                .from(Sftp.inboundAdapter(this.sftpSessionFactory())
                        .preserveTimestamp(true)
                        .remoteDirectory(sftpRemoteDirectoryBycstmAgre)
                        .regexFilter(".*\\.txt$")
                        //.localFilenameExpression("#this.toUpperCase() + '.a'")
                        .localDirectory(new File(sftpLocalDirectoryBycstmAgre)),
                        e -> e.id("sftpInboundAdapterBycstmAgre")
                        .autoStartup(true)
                        .poller(Pollers.fixedDelay(10000)))
                .handle(m -> System.out.println(m.getPayload() + " | handler BycstmAgre" ))
                .get();
    }


    /**
     * sales 고객동의코드
     *
     * @return
     */
    @Bean
    public IntegrationFlow sftpInboundCstmrAgreCodeFlow() {

        return IntegrationFlows
                .from(Sftp.inboundAdapter(this.sftpSessionFactory())
                        .preserveTimestamp(true)
                        .remoteDirectory(sftpRemoteDirectoryCstmrAgreCode)
                        .regexFilter(".*\\.txt$")
                        //.localFilenameExpression("#this.toUpperCase() + '.a'")
                        .localDirectory(new File(sftpLocalDirectoryCstmrAgreCode)),
                        e -> e.id("sftpInboundAdapterCstmrAgreCode")
                        .autoStartup(true)
                        .poller(Pollers.fixedDelay(10000)))
                .handle(m -> System.out.println(m.getPayload() + " | handler CstmrAgreCode" ))
                .get();
    }


    /**
     * sales 고객계약서별동의
     *
     * @return
     */
    @Bean
    public IntegrationFlow sftpInboundBycntcAgreFlow() {

        return IntegrationFlows
                .from(Sftp.inboundAdapter(this.sftpSessionFactory())
                        .preserveTimestamp(true)
                        .remoteDirectory(sftpRemoteDirectoryBycntcAgre)
                        .regexFilter(".*\\.txt$")
                        //.localFilenameExpression("#this.toUpperCase() + '.a'")
                        .localDirectory(new File(sftpLocalDirectoryBycntcAgre)),
                        e -> e.id("sftpInboundAdapterBycntcAgre")
                        .autoStartup(true)
                        .poller(Pollers.fixedDelay(10000)))
                .handle(m -> System.out.println(m.getPayload() + " | handler BycntcAgre" ))
                .get();
    }


    /**
     * 참클럽 고객등급
     *
     * @return
     */
    @Bean
    public IntegrationFlow sftpInboundKorOwnersFlagFlow() {

        return IntegrationFlows
                .from(Sftp.inboundAdapter(this.sftpSessionFactory())
                        .preserveTimestamp(true)
                        .remoteDirectory(sftpRemoteDirectoryKorOwnersFlag)
                        .regexFilter(".*\\.txt$")
                        //.localFilenameExpression("#this.toUpperCase() + '.a'")
                        .localDirectory(new File(sftpLocalDirectoryKorOwnersFlag)),
                        e -> e.id("sftpInboundAdapterKorOwnersFlag")
                        .autoStartup(true)
                        .poller(Pollers.fixedDelay(10000)))
                .handle(m -> System.out.println(m.getPayload() + " | handler KorOwnersFlag" ))
                .get();
    }


    /**
     * 참클럽 고객정보
     *
     * @return
     */
    @Bean
    public IntegrationFlow sftpInboundKorOwnersFlagVinFlow() {

        return IntegrationFlows
                .from(Sftp.inboundAdapter(this.sftpSessionFactory())
                        .preserveTimestamp(true)
                        .remoteDirectory(sftpRemoteDirectoryKorOwnersFlagVin)
                        .regexFilter(".*\\.txt$")
                        //.localFilenameExpression("#this.toUpperCase() + '.a'")
                        .localDirectory(new File(sftpLocalDirectoryKorOwnersFlagVin)),
                        e -> e.id("sftpInboundAdapterKorOwnersFlagVin")
                        .autoStartup(true)
                        .poller(Pollers.fixedDelay(10000)))
                .handle(m -> System.out.println(m.getPayload() + " | handler KorOwnersFlagVin" ))
                .get();
    }


    /**
     * 참클럽 멤버
     *
     * @return
     */
    @Bean
    public IntegrationFlow sftpInboundKorOwnersMemberFlow() {

        return IntegrationFlows
                .from(Sftp.inboundAdapter(this.sftpSessionFactory())
                        .preserveTimestamp(true)
                        .remoteDirectory(sftpRemoteDirectoryKorOwnersMember)
                        .regexFilter(".*\\.txt$")
                        //.localFilenameExpression("#this.toUpperCase() + '.a'")
                        .localDirectory(new File(sftpLocalDirectoryKorOwnersMember)),
                        e -> e.id("sftpInboundAdapterKorOwnersMember")
                        .autoStartup(true)
                        .poller(Pollers.fixedDelay(10000)))
                .handle(m -> System.out.println(m.getPayload() + " | handler KorOwnersMember" ))
                .get();
    }





    public static void main(String[] args) {

        SpringApplication.run(IntegrationApplication.class, args);
    }

}
