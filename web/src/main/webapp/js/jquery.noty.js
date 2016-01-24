(function($){$.noty=function(options,customContainer){var base={};var $noty=null;var isCustom=false;base.init=function(options){base.options=$.extend({},$.noty.defaultOptions,options);base.options.type=base.options.cssPrefix+base.options.type;base.options.id=base.options.type+'_'+new Date().getTime();base.options.layout=base.options.cssPrefix+'layout_'+base.options.layout;if(base.options.custom.container)customContainer=base.options.custom.container;isCustom=($.type(customContainer)==='object')?true:false;return base.addQueue();};base.addQueue=function(){var isGrowl=($.inArray(base.options.layout,$.noty.growls)==-1)?false:true;if(!isGrowl)(base.options.force)?$.noty.queue.unshift({options:base.options}):$.noty.queue.push({options:base.options});return base.render(isGrowl);};base.render=function(isGrowl){var container=(isCustom)?customContainer.addClass(base.options.theme+' '+base.options.layout+' noty_custom_container'):$('body');if(isGrowl){if($('ul.noty_cont.'+ base.options.layout).length==0)
container.prepend($('<ul/>').addClass('noty_cont '+ base.options.layout));container=$('ul.noty_cont.'+ base.options.layout);}else{if($.noty.available){var fromQueue=$.noty.queue.shift();if($.type(fromQueue)==='object'){$.noty.available=false;base.options=fromQueue.options;}else{$.noty.available=true;return base.options.id;}}else{return base.options.id;}}
base.container=container;base.bar=$('<div class="noty_bar"/>').attr('id',base.options.id).addClass(base.options.theme+' '+base.options.layout+' '+base.options.type);$noty=base.bar;$noty.append(base.options.template).find('.noty_text').html(base.options.text);$noty.data('noty_options',base.options);(base.options.closeButton)?$noty.addClass('noty_closable').find('.noty_close').show():$noty.find('.noty_close').remove();$noty.find('.noty_close').bind('click',function(){$noty.trigger('noty.close');});if(base.options.buttons)base.options.closeOnSelfClick=base.options.closeOnSelfOver=false;if(base.options.closeOnSelfClick)$noty.bind('click',function(){$noty.trigger('noty.close');}).css('cursor','pointer');if(base.options.closeOnSelfOver)$noty.bind('mouseover',function(){$noty.trigger('noty.close');}).css('cursor','pointer');if(base.options.buttons){$buttons=$('<div/>').addClass('noty_buttons');$noty.find('.noty_message').append($buttons);$.each(base.options.buttons,function(i,button){bclass=(button.type)?button.type:'gray';$button=$('<button/>').addClass(bclass).html(button.text).appendTo($noty.find('.noty_buttons')).bind('click',function(){if($.isFunction(button.click)){button.click.call($button,$noty);}});});}
return base.show(isGrowl);};base.show=function(isGrowl){if(base.options.modal)$('<div/>').addClass('noty_modal').addClass(base.options.theme).prependTo($('body')).fadeIn('fast');$noty.close=function(){return this.trigger('noty.close');};(isGrowl)?base.container.prepend($('<li/>').append($noty)):base.container.prepend($noty);if(base.options.layout=='noty_layout_topCenter'||base.options.layout=='noty_layout_center'){$.noty.reCenter($noty);}
$noty.bind('noty.setText',function(event,text){$noty.find('.noty_text').html(text);if(base.options.layout=='noty_layout_topCenter'||base.options.layout=='noty_layout_center'){$.noty.reCenter($noty);}});$noty.bind('noty.setType',function(event,type){$noty.removeClass($noty.data('noty_options').type);type=$noty.data('noty_options').cssPrefix+type;$noty.data('noty_options').type=type;$noty.addClass(type);if(base.options.layout=='noty_layout_topCenter'||base.options.layout=='noty_layout_center'){$.noty.reCenter($noty);}});$noty.bind('noty.getId',function(event){return $noty.data('noty_options').id;});$noty.one('noty.close',function(event){var options=$noty.data('noty_options');if(options.onClose){options.onClose();}
if(options.modal)$('.noty_modal').fadeOut('fast',function(){$(this).remove();});$noty.clearQueue().stop().animate($noty.data('noty_options').animateClose,$noty.data('noty_options').speed,$noty.data('noty_options').easing,$noty.data('noty_options').onClosed).promise().done(function(){if($.inArray($noty.data('noty_options').layout,$.noty.growls)>-1){$noty.parent().remove();}else{$noty.remove();$.noty.available=true;base.render(false);}});});if(base.options.onShow){base.options.onShow();}
$noty.animate(base.options.animateOpen,base.options.speed,base.options.easing,base.options.onShown);if(base.options.timeout)$noty.delay(base.options.timeout).promise().done(function(){$noty.trigger('noty.close');});return base.options.id;};return base.init(options);};$.noty.get=function(id){return $('#'+id);};$.noty.close=function(id){for(var i=0;i<$.noty.queue.length;){if($.noty.queue[i].options.id==id)
$.noty.queue.splice(id,1);else
i++;}
$.noty.get(id).trigger('noty.close');};$.noty.setText=function(id,text){$.noty.get(id).trigger('noty.setText',text);};$.noty.setType=function(id,type){$.noty.get(id).trigger('noty.setType',type);};$.noty.closeAll=function(){$.noty.clearQueue();$('.noty_bar').trigger('noty.close');};$.noty.reCenter=function(noty){noty.css({'left':($(window).width()- noty.outerWidth())/ 2 + 'px'});
};$.noty.clearQueue=function(){$.noty.queue=[];};var windowAlert=window.alert;$.noty.consumeAlert=function(options){window.alert=function(text){if(options){options.text=text;}
else{options={text:text};}
$.noty(options);};}
$.noty.stopConsumeAlert=function(){window.alert=windowAlert;}
$.noty.queue=[];$.noty.growls=['noty_layout_topLeft','noty_layout_topRight','noty_layout_bottomLeft','noty_layout_bottomRight'];$.noty.available=true;$.noty.defaultOptions={layout:'top',theme:'noty_theme_default',animateOpen:{height:'toggle'},animateClose:{height:'toggle'},easing:'swing',text:'',type:'alert',speed:500,timeout:5000,closeButton:false,closeOnSelfClick:true,closeOnSelfOver:false,force:false,onShow:false,onShown:false,onClose:false,onClosed:false,buttons:false,modal:false,template:'<div class="noty_message"><span class="noty_text"></span><div class="noty_close"></div></div>',cssPrefix:'noty_',custom:{container:null}};$.fn.noty=function(options){return this.each(function(){(new $.noty(options,$(this)));});};})(jQuery);function noty(options){return jQuery.noty(options);}