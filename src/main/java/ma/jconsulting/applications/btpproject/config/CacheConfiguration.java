package ma.jconsulting.applications.btpproject.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import org.hibernate.cache.jcache.ConfigSettings;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, ma.jconsulting.applications.btpproject.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, ma.jconsulting.applications.btpproject.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, ma.jconsulting.applications.btpproject.domain.User.class.getName());
            createCache(cm, ma.jconsulting.applications.btpproject.domain.Authority.class.getName());
            createCache(cm, ma.jconsulting.applications.btpproject.domain.User.class.getName() + ".authorities");
            createCache(cm, ma.jconsulting.applications.btpproject.domain.Banque.class.getName());
            createCache(cm, ma.jconsulting.applications.btpproject.domain.Bopportunity.class.getName());
            createCache(cm, ma.jconsulting.applications.btpproject.domain.Bopportunity.class.getName() + ".maitreOuvrages");
            createCache(cm, ma.jconsulting.applications.btpproject.domain.Bopportunity.class.getName() + ".companies");
            createCache(cm, ma.jconsulting.applications.btpproject.domain.Caution.class.getName());
            createCache(cm, ma.jconsulting.applications.btpproject.domain.Caution.class.getName() + ".bopportuniys");
            createCache(cm, ma.jconsulting.applications.btpproject.domain.Caution.class.getName() + ".maitreouvrages");
            createCache(cm, ma.jconsulting.applications.btpproject.domain.Caution.class.getName() + ".banques");
            createCache(cm, ma.jconsulting.applications.btpproject.domain.Caution.class.getName() + ".projects");
            createCache(cm, ma.jconsulting.applications.btpproject.domain.Company.class.getName());
            createCache(cm, ma.jconsulting.applications.btpproject.domain.Delivery.class.getName());
            createCache(cm, ma.jconsulting.applications.btpproject.domain.Delivery.class.getName() + ".projects");
            createCache(cm, ma.jconsulting.applications.btpproject.domain.Delivery.class.getName() + ".orders");
            createCache(cm, ma.jconsulting.applications.btpproject.domain.MaitreOuvrage.class.getName());
            createCache(cm, ma.jconsulting.applications.btpproject.domain.ModePaiement.class.getName());
            createCache(cm, ma.jconsulting.applications.btpproject.domain.Orders.class.getName());
            createCache(cm, ma.jconsulting.applications.btpproject.domain.Orders.class.getName() + ".projects");
            createCache(cm, ma.jconsulting.applications.btpproject.domain.Orders.class.getName() + ".providers");
            createCache(cm, ma.jconsulting.applications.btpproject.domain.OrdersProducts.class.getName());
            createCache(cm, ma.jconsulting.applications.btpproject.domain.OrdersProducts.class.getName() + ".orders");
            createCache(cm, ma.jconsulting.applications.btpproject.domain.OrdersProducts.class.getName() + ".products");
            createCache(cm, ma.jconsulting.applications.btpproject.domain.OrdersServices.class.getName());
            createCache(cm, ma.jconsulting.applications.btpproject.domain.OrdersServices.class.getName() + ".orders");
            createCache(cm, ma.jconsulting.applications.btpproject.domain.OrdersServices.class.getName() + ".services");
            createCache(cm, ma.jconsulting.applications.btpproject.domain.Products.class.getName());
            createCache(cm, ma.jconsulting.applications.btpproject.domain.Products.class.getName() + ".recommendedProviders");
            createCache(cm, ma.jconsulting.applications.btpproject.domain.Project.class.getName());
            createCache(cm, ma.jconsulting.applications.btpproject.domain.Project.class.getName() + ".companies");
            createCache(cm, ma.jconsulting.applications.btpproject.domain.Project.class.getName() + ".bopportuniys");
            createCache(cm, ma.jconsulting.applications.btpproject.domain.Provider.class.getName());
            createCache(cm, ma.jconsulting.applications.btpproject.domain.Reglement.class.getName());
            createCache(cm, ma.jconsulting.applications.btpproject.domain.Reglement.class.getName() + ".providers");
            createCache(cm, ma.jconsulting.applications.btpproject.domain.Reglement.class.getName() + ".projects");
            createCache(cm, ma.jconsulting.applications.btpproject.domain.Reglement.class.getName() + ".orders");
            createCache(cm, ma.jconsulting.applications.btpproject.domain.Reglement.class.getName() + ".deliveries");
            createCache(cm, ma.jconsulting.applications.btpproject.domain.Reglement.class.getName() + ".modePaiements");
            createCache(cm, ma.jconsulting.applications.btpproject.domain.Services.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache == null) {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

}
