package com.eucalyptus.auth.policy.key;

import java.util.Map;
import java.util.Set;
import org.apache.log4j.Logger;
import com.eucalyptus.auth.Contract;
import com.google.common.collect.Maps;

public class ContractKeyEvaluator {
  
  private static final Logger LOG = Logger.getLogger( ContractKeyEvaluator.class );

  private Map<String, Contract> contracts;
  
  public ContractKeyEvaluator( ) {
    this.contracts = Maps.newHashMap( );
  }
  
  public ContractKeyEvaluator( Map<String, Contract> contracts ) {
    this.contracts = contracts;
  }
  
  public void addContract( ContractKey contractKey, Set<String> values ) {
    Contract update = contractKey.getContract( values.toArray( new String[0] ) );
    Contract current = contracts.get( update.getName( ) );
    if ( current == null || contractKey.isBetter( current, update ) ) {
      contracts.put( update.getName( ), update );
    }
  }
  
  public Map<String, Contract> getContracts( ) {
    return this.contracts;
  }
  
}