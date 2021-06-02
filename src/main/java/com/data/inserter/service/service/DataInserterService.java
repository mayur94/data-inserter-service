package com.data.inserter.service.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.data.inserter.service.config.MessagingConfig;
import com.data.inserter.service.model.EmailResponse;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DataInserterService {
	

	@Autowired
	private DataSource datasource;
	
	
	  @RabbitListener(queues = MessagingConfig.OUTPUT_QUEUE) 
	  public void consumeMessageFromOutputQueue(EmailResponse emailResponse) throws SQLException {
	  String SQL_INSERT = new String("insert into emailaddressbatch.emailvalidated (input, is_reachable) values (?, ?)");
	 
		log.info("Email Response in for data inserter service"+emailResponse.getInput());
		  try ( 
				  Connection connection = datasource.getConnection(); 
				  PreparedStatement statement = connection.prepareStatement(SQL_INSERT); 
				  ) 
					{

						statement.setString(1, emailResponse.getInput());
						statement.setString(2, emailResponse.getIs_reachable());

						statement.addBatch();

						statement.executeBatch();
					}
		 log.info("Data Inserted successfully in DB"+emailResponse.getInput());
	  
	  }
	 

}
