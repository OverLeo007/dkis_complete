package ru.paskal.Lab3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.paskal.Lab3.Dependences.Memory;
import ru.paskal.Lab3.Dependences.Processor;

@Configuration
@ComponentScan("ru.paskal.Lab3")
@PropertySource("classpath:microchip.properties")
public class AppConfig {

  @Value("${memory.size}")
  private int memorySize;

  @Value("${cpu.modelName}")
  private String cpuModelName;

  @Value("${cpuMicrochip.modelName}")
  private String cpuMicrochipModelName;

  @Value("${cpuMicrochip.id}")
  private int cpuMicrochipId;

  @Value("${ramMicrochip.modelName}")
  private String ramMicrochipModelName;

  @Value("${ramMicrochip.id}")
  private int ramMicrochipId;

  @Bean
  public Memory memoryBean() {
    return new Memory(memorySize);
  }

  @Bean
  public Processor cpuBean() {
    return Processor.processorFactory(cpuModelName);
  }

  @Bean
  public Microchip cpuMicrochip() {
    Microchip cpuMicrochip = new Microchip(cpuBean(), cpuMicrochipModelName);
    cpuMicrochip.setId(cpuMicrochipId);
    return cpuMicrochip;
  }

  @Bean
  public Microchip ramMicrochip() {
    Microchip ramMicrochip = new Microchip(memoryBean(), ramMicrochipModelName);
    ramMicrochip.setId(ramMicrochipId);
    return ramMicrochip;
  }
}
