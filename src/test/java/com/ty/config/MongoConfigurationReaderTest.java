package com.ty.config;

import com.ty.enums.Type;
import com.ty.model.Configuration;
import com.ty.repository.ConfigurationRepository;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MongoConfigurationReaderTest {

    @InjectMocks
    private MongoConfigurationReader mongoConfigurationReader;

    @Mock
    private ConfigurationRepository configurationRepository;

    @Test
    public void shouldLoadMap() {
        mongoConfigurationReader.setApplicationName("ty-application");

        Configuration configuration = Configuration.builder()
                .applicationName("ty-application")
                .value("www.trendyol.com")
                .name("siteName")
                .type(Type.STRING)
                .build();

        when(configurationRepository.findByApplicationNameAndIsActiveTrue("ty-application"))
                .thenReturn(Lists.newArrayList(configuration));

        mongoConfigurationReader.loadValues();

        assertThat(mongoConfigurationReader.getValue("siteName"), equalTo("www.trendyol.com"));
    }


    @Test
    public void shouldRemainOldValuesAfterException() {
        mongoConfigurationReader.setApplicationName("ty-application");

        Configuration configuration = Configuration.builder()
                .applicationName("ty-application")
                .value("www.trendyol.com")
                .name("siteName")
                .type(Type.STRING)
                .build();

        when(configurationRepository.findByApplicationNameAndIsActiveTrue("ty-application"))
                .thenReturn(Lists.newArrayList(configuration))
                .thenThrow(new RuntimeException("Error happened"));

        mongoConfigurationReader.loadValues();
        mongoConfigurationReader.loadValues();

        assertThat(mongoConfigurationReader.getValue("siteName"), equalTo("www.trendyol.com"));
    }

    @Test
    public void shouldLoadAllTypesOfConfiguration() {
        mongoConfigurationReader.setApplicationName("ty-application");

        Configuration configurationStr = Configuration.builder()
                .applicationName("ty-application")
                .value("www.trendyol.com")
                .name("siteName")
                .type(Type.STRING)
                .build();

        Configuration configurationInt = Configuration.builder()
                .applicationName("ty-application")
                .value("1")
                .name("int")
                .type(Type.INTEGER)
                .build();

        Configuration configurationBool = Configuration.builder()
                .applicationName("ty-application")
                .value("true")
                .name("bool")
                .type(Type.BOOLEAN)
                .build();

        Configuration configurationDouble = Configuration.builder()
                .applicationName("ty-application")
                .value("3.25")
                .name("double")
                .type(Type.DOUBLE)
                .build();


        when(configurationRepository.findByApplicationNameAndIsActiveTrue("ty-application"))
                .thenReturn(Lists.newArrayList(configurationStr, configurationDouble, configurationInt, configurationBool));

        mongoConfigurationReader.loadValues();

        assertThat(mongoConfigurationReader.getValue("siteName"), equalTo("www.trendyol.com"));
        assertThat(mongoConfigurationReader.getValue("int"), equalTo(1));
        assertThat(mongoConfigurationReader.getValue("bool"), equalTo(Boolean.TRUE));
        assertThat(mongoConfigurationReader.getValue("double"), equalTo(Double.valueOf(3.25)));
    }

}