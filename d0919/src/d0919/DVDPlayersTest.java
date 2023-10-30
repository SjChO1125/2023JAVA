package d0919;


class DVDPlayersTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DVDPlayer a= new DVDPlayer();
		Players b= new DVDPlayer();
		ExPlayers c= new DVDPlayer();

    System.out.println("DVDPlayer형 변수 a");
    a.play();
    a.stop();
    a.slow();

    System.out.println("Player형 변수 b");
    b.play();
    b.stop();
   
    System.out.println("ExPlayer형 변수 c");
    c.play();
    c.stop();
    c.slow();


	}

}
