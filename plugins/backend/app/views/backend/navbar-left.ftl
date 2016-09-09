<div class="navbar-default sidebar" role="navigation">
    <div class="sidebar-nav navbar-collapse">
        <ul class="nav" id="side-menu">
			<#list trees as tree>
                <li>
                    <a href="#${tree.getModule() ! ''}"><i class="fa fa-dashboard fa-fw"></i> ${tree.getName() !''}</a>
                </li>
			</#list>
        </ul>
    </div>
    <!-- /.sidebar-collapse -->
</div>