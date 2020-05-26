package com.registerbook.registerbook;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RegisterbookApplicationTests {

	@Test
	void contextLoads() {

	}
	/*@Test
	public void testNameSeparations(){
		MemberServiceImplementation memberServiceImplementation = new MemberServiceImplementation();
		String inputName = "Letenyei Adam";
		String[] separated = memberServiceImplementation.separateName(inputName);
		String f = separated[0];
		String l = separated[1];
		System.out.println("Visszakapott vezetéknév: " + f);
		System.out.println("Visszakapott keresztnév " + l);
		//assertEquals("Letenyei",f);
		//assertEquals("Adam",l);
		assertTrue("Letenyei".equals(f));
		assertTrue("Adam".equals(l));
	}
	*/

	/*@Test
	public void testGetMember(){
		Member test = new Member();
		MemberServiceImplementation ms = new MemberServiceImplementation();
		test.setId(new Long(1));
		test.setFirstName("Letenyei");
		test.setLastName("Adam");
		test.setBand("Amon amarth");
		test.setAddress("Bszh");
		test.setEmail("letenyeidam89@gmail.com");
		test.setFavouriteAnimal("dog");
		test.setFavouriteMeal("teszta");
		List<Member> testList = new ArrayList<>();
		testList.add(test);
		Member searched = ms.findByName("Letenyei Adam",testList);
		String testName = "Letenyei Adam";
		System.out.println(searched.getFirstName() + " " + searched.getLastName());
		assertEquals(test,searched);
	}
	*/


}
