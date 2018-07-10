package loja;
public class TesteDto {
	@Deployment
	public static void TesteDto () {
		Testar();
		Testar2();
		
	}
	
	@Test
	public void Testar() throws Exception {

		log.info("Teste");
	}
	@Test
	public void Testar2() throws Exception {

		log.info("Teste 2");
	}

}