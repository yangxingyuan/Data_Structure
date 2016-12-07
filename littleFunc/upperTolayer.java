// /reg/opt/v1.0/this/is/a/test?am=false&pm=true
// 可以匹配 /reg/opt/v1.0

// /reg/opt/v1.0/this/is/a/test?am=false&pm=true
// /reg/opt/v1.0/this/is/a
// /reg/opt/v1.0/this/is
// /reg/opt/v1.0/this
// /reg/opt/v1.0   find 

// synapse dispathToapi 的流程中用户输入的uri匹配api的context和version
// 使用遍历当前所有api，进行低效的字符串匹配
// 现在使用hashmap 存储每个api.context + api.version

String upperToLayer ( String path )
{
	// path is "/"
	if ( path == null || path.length() == 1 )
	{
		return null;
	}

	for ( int curlen = path.length(); 1 < curlen; --curlen )
	{
		if ( path.charAt(curlen - 1) == '/' || path.charAt(curlen - 1) == '?' )
		{
			break;
		}
	}

	if ( 1 == curlen )
	{
		return null;
	}

	if ( path.charAt(curlen - 1) == '/' && path.charAt(curlen - 2) == '?' )
	{
		curlen = curlen - 2;
	} else {
		curlen = curlen - 1;
	}

	return path.subString(0, curlen);
}

API getAPI ( String path, hashmap<String, API> map )
{
	if ( map.size() == 0 )
	{
		return null;
	}

	API api = null;
	while ( path != null ) 
	{
		API api = map.get(path);
		if ( api == null )
		{
			path = upperToLayer(path);
		} else {
			break;
		}
	}
	return api;
}

