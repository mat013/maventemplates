#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.services.msg;

public interface TextMessageUsingAdapterListener {
    void onReceive(String message);
}