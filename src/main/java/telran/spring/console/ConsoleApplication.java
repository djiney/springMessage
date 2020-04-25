package telran.spring.console;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import telran.spring.console.dto.TypesResponse;
import telran.spring.console.services.messages.MessageService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class ConsoleApplication implements CommandLineRunner
{
	private final MessageService service;

	public static final int MAX_THREADS = 3000;
	public static final int MAX_CYCLES = 10;

	public ConsoleApplication(MessageService service)
	{
		this.service = service;
	}

	public static void main(String[] args)
	{
		new SpringApplicationBuilder(ConsoleApplication.class)
			.web(WebApplicationType.NONE)
			.run(args);
	}

	@Override
	public void run(String... args) throws Exception
	{
		int sleep, cycle = 0;

		service.handshake();

		while (true) {
			runThreads();

			if (cycle++ > MAX_CYCLES) {
				break;
			}

			sleep = rand(10);
			System.out.printf("Pause before next launch: %ds\n", sleep);
			TimeUnit.SECONDS.sleep(sleep);
		}
	}

	private void runThreads() throws InterruptedException
	{
		int count = rand(MAX_THREADS);
		System.out.printf("\nThreads to run: %d\n", count);

		ExecutorService executor = Executors.newFixedThreadPool(count);
		for (int i = 0; i < count; i++)
		{
			executor.execute(() -> {
				TypesResponse response = service.getTypes();
				System.out.print(response.isOK() ? "+" : "-");
			});
		}

		executor.shutdown();

		while (!executor.isTerminated()) {
			TimeUnit.SECONDS.sleep(1);
		}

		System.out.println("");
	}

	private int rand(int multiplier)
	{
		return (int) (Math.random() * multiplier);
	}
}
