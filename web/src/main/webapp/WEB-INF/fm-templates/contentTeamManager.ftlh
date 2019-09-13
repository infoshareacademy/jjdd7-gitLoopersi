<div>
  <script src="https://code.jquery.com/jquery-3.4.0.min.js"></script>
  <script src="/js/team.js"></script>
  <article id="TRESC">

    <table>
      <tr>
        <td style="text-align: left" width="100%"><h2>Team manager</h2></td>
        <td style="text-align: right" width="100%">
          <button type="button" class="btn btn-outline-secondary"
                  data-toggle="modal" data-target="#exampleModal" id="add_team_button">
            <img src="/img/button_add.svg" alt="Add"> Add new team
          </button>

        </td>
      </tr>
    </table>

    <table class="table table-sm" style="text-align: center">

      <thead>
      <tr>
        <th scope="col">#</th>
        <th scope="col">Id</th>
        <th scope="col">Name</th>
        <th scope="col">Manage</th>
      </tr>
      </thead>
      <tbody>
      <#list teams>
          <#items as team>
            <tr>
              <td>${team_index + 1}</td>
              <td>${team.id}</td>
              <td>${team.name}</td>
              <td>
                <div>
                  <a class="edit-team" data-id="${team.id}" href="#" data-toggle="modal"
                     data-target="#exampleModal">
                    <img src="/img/button_edit.svg" id="label" alt="Edit" title="Edit"></a>
                  <a class="delete-team" data-id="${team.id}" href="#">
                    <img src="/img/button_delete.svg" alt="Delete" title="Delete"></a>
                </div>
              </td>
            </tr>
          </#items>
      </#list>
      </tbody>
    </table>

    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
         aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">
              Team
            </h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <form id="settingForm" name="contact" role="form">
              <input id="formMethod" type="hidden" name="method" value="${method!"post"}"/>
              <input type="hidden" id="id" name="id" value="${(team.id)!""}"/>
              <div class="form-group">
                <label for="name">Name:
                  <input type="text" id="name" class="form-control" name="name"
                         placeholder="New team name" value="${(team.name)!""}">
                </label>

            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-outline-secondary" data-dismiss="modal">Close
            </button>
            <button type="button" class="btn btn-outline-secondary" id="saveTeam">Save changes
            </button>
          </div>
        </div>
      </div>
    </div>

  </article>
</div>