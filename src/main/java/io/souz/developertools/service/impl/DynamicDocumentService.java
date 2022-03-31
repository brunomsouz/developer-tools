package io.souz.developertools.service.impl;

import io.souz.developertools.exception.NotFoundException;
import io.souz.developertools.service.DocumentService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.stereotype.Service;

@Service
public class DynamicDocumentService {

    private final BeanFactory beanFactory;

    public DynamicDocumentService(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public DocumentService getBean(String name) {
        DocumentService service;

        try {
            service = beanFactory.getBean(name, DocumentService.class);
        } catch (NoSuchBeanDefinitionException e) {
            throw new NotFoundException("2");
        }

        return service;
    }

}
